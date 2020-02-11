module(...,package.seeall)

require "utils"
require "pm"
require "httpv2"

-- 管脚初始化   
local function initGpio(pinsConfig)
    for i,v in pairs(pinsConfig) do
        
        if v["method"] == 0 then
            -- method = 0 为input
            -- 中断后发送到mqtt中

            pins.setup(v["gpio"],function(msg)

                data = {
                    method = "gpioInput",
                    gpio=v["gpio"],
                    state =( msg-1)
                }
                
                sys.publish("mqttPublish",json.encode(data))

                end,pio.PULLUP)
            log.info("pinsConfig input",v["gpio"])


        elseif v["method"] == 1 then
            -- method = 1 为output
            pins.setup(v["gpio"],v["default"])
            log.info("pinsConfig output",v["gpio"],v["default"])
        end

        
    end
end

-- 读取Uart
local function taskReadUart(uartId)
    local cacheData,frameCnt = "",0
    while true do
        local s = uart.read(uartId,"*l")
        if s == "" then
            uart.on(uartId,"receive",function() sys.publish("UART_RECEIVE") end)
            if not sys.waitUntil("UART_RECEIVE",100) then
    
                if cacheData:len()>0 then
                    log.info("testUartTask.taskRead","200ms no data, received length",cacheData:len())

                    readData = cacheData:sub(1,1024)

                    data = {
                        method = "readUart",
                        id=uartId,
                        data =readData
                    }
                    
                    sys.publish("mqttPublish",json.encode(data))

                    cacheData = ""
                    frameCnt = frameCnt+1
                end
            end
            uart.on(uartId,"receive")
        else
            cacheData = cacheData..s            
        end
    end
end

function initUart(uartConfig)
    if #uartConfig>0 then
        pm.wake("uart")
        for i,v in pairs(uartConfig) do            
            log.debug("readUart init", v["id"],v["baud"],v["databits"],v["parity"],v["stopbits"])
            uart.setup(v["id"],v["baud"],v["databits"],v["parity"],v["stopbits"])
            if v["method"] == 1 then
                -- 读取uart
                sys.taskInit(taskReadUart,v["id"])
            elseif v["method"] == 0 then
                
            end

        end
    end   
end




function split( str,reps )
    local resultStrList = {}
    string.gsub(str,'[^'..reps..']+',function ( w )
        table.insert(resultStrList,w)
    end)
    return resultStrList
end


-- 通过HTTP 获得配置
function getConfig() 

    local url = "http://s5.nsloop.com:30411/init"

    local timeout = 1000*3

    local params = {
        imei=misc.getImei(),
        firmwareName = _G.PROJECT,
        version=_G.VERSION,
        deviceKey=misc.getSn()
    }

    local data = nil

    local ctype = 1


    local c, h, b= httpv2.request("GET", url, timeout, params, data, ctype, nil, nil, nil, nil)

    local jsondata,result,errinfo = json.decode(split(b,"\r\n")[2])

    if jsondata.meta.success then
        return  jsondata.data
    else
        return false    
    end


end


-- 获取配置文件对管脚进行配置
sys.taskInit(function()

    while not socket.isReady() do
        --等待网络部署完毕 十秒
        sys.waitUntil("IP_READY_IND",1000*10)
    end

    config = getConfig()
    if config then
        nvm.init("config.lua")
        nvm.set("pinsConfig",config.pinsConfig)
        nvm.set("uartConfig",config.uartConfig)
        nvm.flush()

        initGpio(config.pinsConfig)
        initUart(config.uartConfig)

    end

    


end)



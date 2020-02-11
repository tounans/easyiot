--- 模块功能：MQTT客户端数据接收处理
-- @author openLuat
-- @module mqtt.mqttInMsg
-- @license MIT
-- @copyright openLuat
-- @release 2018.03.28

module(...,package.seeall)


local function handleMsg(msg)
    local jsondata,result,errinfo = json.decode(msg)

    if result then        
        log.info("mqttInMsg",msg)
        local data = jsondata["data"]
        
        if jsondata["method"] == "tts" then
            if string.len(data)>0 then
                audio.play(1,"TTS",data,7)
            end            
            
            -- 播放tts----------------------------------------------
        elseif jsondata["method"] == "gpioOutput" then
            pins.setup(data["gpio"], data["state"])
            -- 电平输出---------------------------------------------
        elseif jsondata["method"] == "getGpioVal" then
            local gpioVal = pio.pin.getval(data)

            msgData = {
                method = "gpioInput",
                gpio=data,
                state =gpioVal
            }
            
            sys.publish("mqttPublish",json.encode(msgData))
            -- 查询电平---------------------------------------------
        elseif jsondata["method"] == "uartOutput" then
            uart.write(data["uart"], data["msg"])
        end
    end
end


--- MQTT客户端数据接收处理
-- @param mqttClient，MQTT客户端对象
-- @return 处理成功返回true，处理出错返回false
-- @usage mqttInMsg.proc(mqttClient)
function proc(mqttClient)
    local result,data
    while true do
        result,data = mqttClient:receive(2000)
        --接收到数据
        if result then

            handleMsg(data.payload)
            --TODO：根据需求自行处理data.payload
            
            --如果mqttOutMsg中有等待发送的数据，则立即退出本循环
            if mqttOutMsg.waitForSend() then return true end
        else
            break
        end
    end
	
    return result or data=="timeout"
end

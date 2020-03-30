PROJECT = "universal_pins"
VERSION = "1.0.0"
PRODUCT_KEY = "1"


--加载日志功能模块，并且设置日志输出等级
--如果关闭调用log模块接口输出的日志，等级设置为log.LOG_SILENT即可
require "log"
-- LOG_LEVEL = log.LOG_SILENT
LOG_LEVEL = log.LOGLEVEL_TRACE

require "sys"
require "pins"
require "nvm"
require "audio"
require "misc"
require "config"
require "mqttTask"
require "init"

-- 同步时间
require "ntp"
ntp.timeSync(1)

-- 看门狗
require "wdt"
wdt.setup(pio.P0_30, pio.P0_31)



_G.initUrl  = "http://localhost/init"
_G.mqttHost = "127.0.0.1"
_G.mqttPort = "22598"
_G.mqttUser = "admin"
_G.mqttPass = "public"

--启动系统框架
sys.init(0, 0)
sys.run()

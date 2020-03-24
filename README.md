
<h1 align="center">Easy-iot</h1>  
  
 |模块|端口|是否暴露|描述   |
 | ------------- | ------------- | -------------  | -------------  |
 |server-gateway|80|是|Zuul|
 |server-config|8010|否|系统设置|
 |server-device|8020|否|设备设置|
 |server-mq|8030|否|MQTT处理|
 |server-auth|8040|是|用于登录|
 |server-push|8050|否|信息推送|
 |server-user|8060|否|用户信息|
 |server-wss|8070,8088|是|WebSocket|
 |server-init|8090|否|设备初始化|
 |server-server|8761|否|Eureka|
 
**目录介绍**
--- 
 |目录|介绍| 
 | ------------- | ------------- | 
 |data/img |运行后效果图|
 |data/key|生成Key|
 |data/lua|硬件源码|
 |data/sql|数据库|
 |data/uni|UNI源码|

**介绍**
---
Easy-Iot是一个分布式物联网平台可使用户免开发对模块进行管脚管理，串口收发等操作。  

Easy-Iot 用到的技术<br/>
1.主要：`Spring Cloud`<br/>
2.安全：`Spring Security`<br/>
3.持久层：`Mybatis plus`、`Redis`<br/>
4.通信：`Netty`<br/>
5.前端：`UNI-App`<br/>
6.UI：`ColorUI`<br/>

编译后可直接在微信小程序、IOS、Android...上运行

目前支持模块：Air202

**截图**
---
 |首页|GPIO|uart|信息   |
 | ------------- | ------------- | -------------  | -------------  |
 |<img src="https://github.com/tounans/easyiot/blob/master/data/img/home.jpg" width="200" />|<img src="https://github.com/tounans/easyiot/blob/master/data/img/gpio_edit.jpg" width="200" />|<img src="https://github.com/tounans/easyiot/blob/master/data/img/uart_edit.jpg" width="200" />|<img src="https://github.com/tounans/easyiot/blob/master/data/img/msg.jpg" width="200" />|

**运行**
---

默认账号 admin 密码 123456

UNI中需要修改的地方如下
```javascript
// js/common.js
const baseUrl = "http://localhost/";
// js/user.js
const baseUrl = "http://localhost:8040/auth";
// pages/index/wss.vue
baseUrl :"wss://127.0.0.1:8088/ws"
```

Java中需要修改的地方
```java
//各个目录中的application.yml
//用 data/key 目录下的 生成Key.txt 生成自己的Key 替换 resource中的 key
// wss 路径设置、心跳时间设置 在server-wss下com/tounans/easyiot/easyiotwss/wss/WssServerInitialzer.java
```

LUA中需要修改的地方
```javascript
//main.lua

_G.initUrl  = "http://localhost/init" 
_G.mqttHost = "127.0.0.1"
_G.mqttPort = "8765"
_G.mqttUser = "username"
_G.mqttPass = "password"

```

本项目仅是给各位和我一样的小白们一个开发思路，很多设计、代码问题请指出我会及时改正。

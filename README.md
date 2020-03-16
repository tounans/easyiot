
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
 

**介绍**
---
Easy-Iot是基于合宙Air202模块可使用户免开发对模块进行管脚管理，串口收发等操作。  

Easy-Iot 用到的技术<br/>
1.主要：`Spring Cloud`<br/>
2.安全：`Spring Security`<br/>
3.持久层：`Mybatis plus`、`Redis`<br/>
4.通信：`Netty`<br/>
5.前端：`UNI-App`<br/>
6.UI：`ColorUI`<br/>

编译后可直接在微信小程序、IOS、Android...上运行

**截图**
---
<img src="https://github.com/tounans/easyiot/blob/master/data/img/home.jpg" width="375" />
<img src="https://github.com/tounans/easyiot/blob/master/data/img/msg.jpg" width="375" />


本项目仅是给各位和我一样的小白们一个开发思路，很多设计、代码问题请指出我会及时改正。

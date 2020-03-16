
<h3 align="center">Easy-iot</h3>
====  
  
基于合宙Air202模块可使用户免开发对模块进行管脚管理，串口收发等操作。  
  
  
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
 

**后续规划**
---
1
--

**已知问题**
---

1
	
**用到的框架**
---


Spring Cloud + Netty + Mybatis + UNI-APP

本项目仅是给各位和我一样的小白们一个开发思路，很多设计、代码问题请指出我会及时改正。

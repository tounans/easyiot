package com.tounans.easyiot.common.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient(value = "server-mq")
public interface MqClient {

    /**
     * 发送tts
     * @param userId
     * @param imei
     * @param msg
     * @return
     */
    @RequestMapping("/send/tts")
    Object sendTTS(@RequestParam("userId")Integer userId,
                    @RequestParam("imei")String imei,
                    @RequestParam("msg")String msg);

    /**
     * 管脚输出
     * @param userId
     * @param imei
     * @param gpio
     * @param state
     * @return
     */
    @RequestMapping("/send/gpioOutput")
    Object gpioOutput(@RequestParam("userId")Integer userId,
                       @RequestParam("imei")String imei,
                       @RequestParam("gpio")Integer gpio,
                       @RequestParam("state")Integer state);

    /**
     * uart输出
     * @param imei
     * @param uart
     * @param msg
     * @return
     */
    @RequestMapping("/send/uartOutput")
    Object uartOutput(@RequestParam("userId")Integer userId,
                       @RequestParam("imei")String imei,
                       @RequestParam("uart")Integer uart,
                       @RequestParam("msg")String msg);
}

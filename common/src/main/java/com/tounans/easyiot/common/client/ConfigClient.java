package com.tounans.easyiot.common.client;

import com.tounans.easyiot.common.entity.config.ConfigMqtt;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "server-config")
public interface ConfigClient {

    /**
     * 通过UserID 获得MQTT Config
     * @param userId
     * @return
     */
    @RequestMapping("/mqtt/getConfigMqttByUserId")
    ConfigMqtt getConfigMqttByUserId(@RequestParam("userId") Integer userId);


    /**
     * 修改或者保存 MQTT  Config
     * @param userId
     * @param configMqtt
     * @return
     */
    @RequestMapping("/mqtt/editAndSaveConfigMqtt")
    boolean editAndSaveConfigMqtt(@RequestParam("userId") Integer userId,
                                  @RequestBody ConfigMqtt configMqtt);


}

package com.tounans.easyiot.easyiotconfig.controller;

import com.tounans.easyiot.common.entity.config.ConfigMqtt;
import com.tounans.easyiot.easyiotconfig.service.IConfigMqttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mqtt")
public class ConfigMqttController {

    @Autowired
    IConfigMqttService configMqttService;


    /**
     * 通过UserID 获得MQTT Config
     * @param userId
     * @return
     */
    @RequestMapping("/getConfigMqttByUserId")
    public ConfigMqtt getConfigMqttByUserId(Integer userId){
        return configMqttService.getByUserId(userId);
    }

    /**
     * 修改或者保存 MQTT  Config
     * @param userId
     * @param configMqtt
     * @return
     */
    @RequestMapping("/editAndSaveConfigMqtt")
    public boolean editAndSaveConfigMqtt(Integer userId,@RequestBody ConfigMqtt configMqtt){
        return configMqttService.editAndSaveConfigMqtt(userId,configMqtt);
    }



}

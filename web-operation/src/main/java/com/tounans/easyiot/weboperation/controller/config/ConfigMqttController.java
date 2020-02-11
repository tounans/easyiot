package com.tounans.easyiot.weboperation.controller.config;

import com.tounans.easyiot.config.service.IConfigMqttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/config/mqtt")
public class ConfigMqttController {

    @Autowired
    IConfigMqttService configMqttService;


    @GetMapping({"","/index"})
    public String index(Model model){
        int userId = 1;
        configMqttService.getByUserId(userId);
        return "admin/config/mqtt/index";
    }

}

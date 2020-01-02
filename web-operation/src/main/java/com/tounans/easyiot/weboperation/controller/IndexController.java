package com.tounans.easyiot.weboperation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class IndexController {

    @GetMapping({"","index"})
    public String index(Model model){
        return "admin/index";
    }
}

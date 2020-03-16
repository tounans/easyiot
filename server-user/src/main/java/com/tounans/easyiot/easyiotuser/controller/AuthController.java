package com.tounans.easyiot.easyiotuser.controller;

import com.tounans.easyiot.easyiotuser.service.IPermissionService;
import com.tounans.easyiot.easyiotuser.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    IUserService userService;

    @Autowired
    IPermissionService permissionService;

    @RequestMapping("/getUserByUserName")
    public Object getUserByUserName(String username){
        return  userService.getByUserName(username);
    }

    @RequestMapping("/getPermissionByUserId")
    public Object getPermissionByUserId(Integer userId){
        return permissionService.getByUserId(userId);
    }



}

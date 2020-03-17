package com.tounans.easyiot.easyiotuser.controller;

import com.tounans.easyiot.common.entity.user.User;
import com.tounans.easyiot.common.entity.user.UserRole;
import com.tounans.easyiot.easyiotuser.service.IPermissionService;
import com.tounans.easyiot.easyiotuser.service.IUserRoleService;
import com.tounans.easyiot.easyiotuser.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    IUserService userService;

    @Autowired
    IPermissionService permissionService;

    @Autowired
    IUserRoleService userRoleService;

    @RequestMapping("/getUserByUserName")
    public Object getUserByUserName(String username){
        return  userService.getByUserName(username);
    }

    @RequestMapping("/getPermissionByUserId")
    public Object getPermissionByUserId(Integer userId){
        return permissionService.getByUserId(userId);
    }

    @RequestMapping("/registered")
    public Object registered(@RequestBody User user){
        User byUserName = userService.getByUserName(user.getUsername());
        if (byUserName == null){
            boolean save = userService.save(user);
            userRoleService.save(new UserRole().setUserId(user.getId().longValue()).setRoleId(1L));
            return true;
        }
        return  false;
    }



}

package com.tounans.easyiot.common.client;

import com.tounans.easyiot.common.entity.user.Permission;
import com.tounans.easyiot.common.entity.user.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(value = "server-user")
public interface UserClient {

    @RequestMapping("/auth/getUserByUserName")
    User AuthGetUserByUserName(@RequestParam("username") String username);

    @RequestMapping("/auth/getPermissionByUserId")
    List<Permission> AuthGetPermissionByUserId(@RequestParam("userId") Integer userId);

    @RequestMapping("/auth/registered")
    Boolean AuthRegistered(@RequestBody User user);

}

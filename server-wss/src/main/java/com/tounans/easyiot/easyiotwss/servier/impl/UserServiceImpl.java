package com.tounans.easyiot.easyiotwss.servier.impl;

import com.tounans.easyiot.common.client.UserClient;
import com.tounans.easyiot.common.entity.user.Permission;
import com.tounans.easyiot.common.model.auth.UserJwt;
import com.tounans.easyiot.easyiotwss.servier.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserClient userClient;

    @Override
    public UserDetails loadUserByUsername(String username){

        com.tounans.easyiot.common.entity.user.User user = userClient.AuthGetUserByUserName(username);
        if(user == null){
            //返回空给spring security表示用户不存在
            return null;
        }
        //取出正确密码（hash值）
        String password = user.getPassword();
        List<Permission> permissions = userClient.AuthGetPermissionByUserId(user.getId());
        if(permissions == null){
            permissions = new ArrayList<>();
        }
        List<String> user_permission = new ArrayList<>();
        permissions.forEach(item-> user_permission.add(item.getEnname()));
        String user_permission_string  = StringUtils.join(user_permission.toArray(), ",");
        UserJwt userDetails = new UserJwt(username,
                password,
                AuthorityUtils.commaSeparatedStringToAuthorityList(user_permission_string));
        userDetails.setId(user.getId());
        return userDetails;
    }


}

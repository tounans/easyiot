package com.tounans.easyiot.easyiotwss.servier.impl;

import com.tounans.easyiot.common.client.UserClient;
import com.tounans.easyiot.common.entity.user.Permission;
import com.tounans.easyiot.common.model.auth.UserJwt;
import com.tounans.easyiot.easyiotwss.servier.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
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
        //这里暂时使用静态密码
//       String password ="123";
        //用户权限，这里暂时使用静态数据，最终会从数据库读取
        //从数据库获取权限
        List<Permission> permissions = userClient.AuthGetPermissionByUserId(user.getId());
        if(permissions == null){
            permissions = new ArrayList<>();
        }
        List<String> user_permission = new ArrayList<>();
        permissions.forEach(item-> user_permission.add(item.getEnname()));
        //使用静态的权限表示用户所拥有的权限
//        user_permission.add("course_get_baseinfo");//查询课程信息
//        user_permission.add("course_pic_list");//图片查询
        String user_permission_string  = StringUtils.join(user_permission.toArray(), ",");
        UserJwt userDetails = new UserJwt(username,
                password,
                AuthorityUtils.commaSeparatedStringToAuthorityList(user_permission_string));
        userDetails.setId(user.getId());
        return userDetails;
    }


}

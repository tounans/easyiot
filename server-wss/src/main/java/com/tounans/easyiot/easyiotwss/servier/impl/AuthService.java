package com.tounans.easyiot.easyiotwss.servier.impl;

import com.alibaba.fastjson.JSON;
import com.tounans.easyiot.common.entity.user.User;
import com.tounans.easyiot.common.model.auth.AuthToken;
import com.tounans.easyiot.common.utlis.JwtUtil;
import com.tounans.easyiot.easyiotwss.servier.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

@Service
public class AuthService implements IAuthService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    //查询令牌的有效期
    public long getExpire(String access_token){
        //key
        String key = "user_token:"+access_token;
        Long expire = stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
        return expire;
    }

    @Override
    public User getUserByToken(String token) {
        User user = null;
        try {
            String key = "user_token:"+token;
            String value = stringRedisTemplate.opsForValue().get(key);
            AuthToken authToken = JSON.parseObject(value, AuthToken.class);
            user = JwtUtil.getUser(JwtUtil.getJwt("D:/key/publickey.txt", authToken.getJwt_token()));
        } catch (Exception e) {
            e.printStackTrace();
            return user;
        }
        return user;
    }
}

package com.tounans.easyiot.easyiotauth.service;

import com.tounans.easyiot.common.model.auth.AuthToken;

public interface IAuthService {


    //用户认证申请令牌，将令牌存储到redis
    AuthToken login(String username, String password, String clientId, String clientSecret);

    //删除token
    boolean delToken(String accessToken);

    //从redis查询令牌
    AuthToken getUserToken(String token);
}

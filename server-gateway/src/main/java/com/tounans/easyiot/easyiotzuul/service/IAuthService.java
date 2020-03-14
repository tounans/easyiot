package com.tounans.easyiot.easyiotzuul.service;

import javax.servlet.http.HttpServletRequest;

public interface IAuthService {


    //从头取出jwt令牌
    String getTokenFromCookie(HttpServletRequest request);

    //从cookie取出token
    //查询身份令牌
    String getJwtFromHeader(HttpServletRequest request);

    //查询令牌的有效期
    long getExpire(String access_token);
}

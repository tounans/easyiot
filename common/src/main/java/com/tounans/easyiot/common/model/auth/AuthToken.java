package com.tounans.easyiot.common.model.auth;

import lombok.Data;

@Data
public class AuthToken {
    private String access_token;//访问token就是短令牌，用户身份令牌
    private String refresh_token;//刷新token
    private String jwt_token;//jwt令牌
}

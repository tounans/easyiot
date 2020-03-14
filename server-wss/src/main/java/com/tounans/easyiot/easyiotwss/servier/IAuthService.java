package com.tounans.easyiot.easyiotwss.servier;

import com.tounans.easyiot.common.entity.user.User;

public interface IAuthService {
    long getExpire(String token);
    User getUserByToken(String token);
}

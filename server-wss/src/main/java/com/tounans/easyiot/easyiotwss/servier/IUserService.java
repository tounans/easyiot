package com.tounans.easyiot.easyiotwss.servier;

import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService {
    UserDetails loadUserByUsername(String username);
}

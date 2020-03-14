package com.tounans.easyiot.easyiotwss.config;

import com.tounans.easyiot.easyiotwss.utils.SpringUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringUtilConfig {
    @Bean
    public SpringUtil getSpingUtil() {
        return new SpringUtil();
    }
}

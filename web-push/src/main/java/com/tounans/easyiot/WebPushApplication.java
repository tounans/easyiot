package com.tounans.easyiot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.tounans.easyiot.webpush.utils.SpringUtil;

@SpringBootApplication
public class WebPushApplication {



    @Bean
    public SpringUtil getSpingUtil() {
        return new SpringUtil();
    }


    public static void main(String[] args) {

        SpringApplication.run(WebPushApplication.class, args);
    }


}

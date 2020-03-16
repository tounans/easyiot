package com.tounans.easyiot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class EasyiotZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyiotZuulApplication.class, args);
    }

}

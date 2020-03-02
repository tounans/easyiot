package com.tounans.easyiot.easyiotdevice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class EasyiotDeviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyiotDeviceApplication.class, args);
    }

}

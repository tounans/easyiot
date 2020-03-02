package com.tounans.easyiot.easyiotmain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
@EnableConfigServer
public class EasyiotMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyiotMainApplication.class, args);
	}

}

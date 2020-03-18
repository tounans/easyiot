package com.tounans.easyiot;

import com.tounans.easyiot.common.config.MybatisPlusConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.tounans.easyiot.*.mapper")
public class EasyiotPushApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyiotPushApplication.class, args);
    }

    @Bean
    public MybatisPlusConfig getMybatisPlusConfig(){
        return new MybatisPlusConfig();
    }
}

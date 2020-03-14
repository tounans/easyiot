package com.tounans.easyiot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class EasyiotAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyiotAuthApplication.class, args);
    }

    //全局配置跨域
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // 允许跨域访问资源定义： /api/ 所有资源
                registry.addMapping("/**")
                        // 只允许本地的9000端口访问
                        .allowedOrigins("*")
                        // 允许发送Cookie
                        .allowCredentials(true)
                        // 允许所有方法
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD");
            }
        };
    }

}

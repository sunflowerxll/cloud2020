package com.sunflower;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer  //启用Eureka服务注册中心
public class EurekaServerMain7002 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerMain7002.class,args);

    }
}
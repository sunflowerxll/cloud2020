package com.sunflower;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer  //启用Eureka服务注册中心
public class EurekaServerMain7001 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerMain7001.class,args);

    }
}

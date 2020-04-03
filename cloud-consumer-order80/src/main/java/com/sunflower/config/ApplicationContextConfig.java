package com.sunflower.config;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {
    //@Bean 相当于 aplicationcontext.xml 中 <bean id="" class="">
    @Bean
    @LoadBalanced   //开启负载均衡(Ribbon),默认采用轮询的方式
    public RestTemplate getTemplate(){
        return new RestTemplate();
    }
}

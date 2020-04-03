package com.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Ribbon Client的主要作用就是使用自定义配置替代Ribbon默认的负载均衡策略
 * 自定义了一个Ribbon Client 它所设定的负载均衡策略只对 "某一特定服务名的服务提供者" 有效
 * 但不能影响服务消费者与别的服务提供者通信所使用的策略
 *  推荐在 springboot 主程序扫描的包范围之外进行自定义配置类 *****
 */

/**
 * 自定义一个ribbon均衡算法
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule(){
        return  new RandomRule(); //随机
    }

}

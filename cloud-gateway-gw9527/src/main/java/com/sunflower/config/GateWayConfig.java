package com.sunflower.config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自己手写一个路由规则
 */
@Configuration
public class GateWayConfig {

    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder){
        RouteLocatorBuilder.Builder routes = builder.routes();

        routes.route("path_routs_demo",
                r->r.path("/guonei")
                .uri("https://news.baidu.com/guonei")).build();

        return routes.build();
    }
}

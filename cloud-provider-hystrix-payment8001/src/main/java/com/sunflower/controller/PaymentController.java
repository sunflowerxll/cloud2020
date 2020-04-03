package com.sunflower.controller;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.utils.FallbackMethod;
import com.sunflower.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    //========================= 服务降级 ========================
    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable Integer id){
        String result = paymentService.paymentInfo_Ok(id);
        log.info("-------- result:"+ result);
        return result;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_timeout(@PathVariable Integer id){
        String result = paymentService.paymentInfo_Timeout(id);
        log.info("-------- result:"+ result);
        return result;
    }

    //========================= 服务熔断 ========================
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable Integer id) {
        String result= paymentService.paymentCircuitBreaker(id);
        log.info("*** result:"+result);
        return result;
    }

}

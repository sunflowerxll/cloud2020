package com.sunflower.controller;


import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sunflower.service.PaymentHystrixServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_Fallback") //配置全局处理方法
public class PaymentHystirxController {


    @Autowired
    private PaymentHystrixServer paymentHystrixServer;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id){
        String result=paymentHystrixServer.paymentInfo_ok(id);
        log.info("80 ++ 线程池:" + Thread.currentThread().getName()+" paymentInfo_ok " +id+"\t");
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
////            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value ="2000" )
////    })
    @HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        String result=paymentHystrixServer.paymentInfo_TimeOut(id);
        log.info("80 ++ 线程池:" + Thread.currentThread().getName()+" paymentInfo_timeout " +id+"\t");
        return result;
    }

    public String paymentInfo_TimeoutHandler(@PathVariable("id") Integer id){
        return "我是消费方,对方支付系统繁忙,请稍后重试... o(╥﹏╥)o";
    }

    public String payment_Global_Fallback(){
        return "我是全局处理方法";
    }


}

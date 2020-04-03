package com.sunflower.controller;

import com.sunflower.entities.CommonResult;
import com.sunflower.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

//    public static final String PAYMENT_URL = "http://192.168.3.42:8002";      //在单机版下,地址可以写死
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";    //在集群环境下,需要换成服务名


    /**
     * @Autowired 与 @Resource
     * @Autowired与@Resource都可以用来装配bean
     *      @Autowired 默认按 类型 装配
     *      @Resource 是JDK1.6支持的注解，默认按照 名称 进行装配
     */

    @Autowired
    private RestTemplate restTemplate;

    /**
     * RestTemplate
     *  .postForObject 直接返回响应体，我们请求时通过泛型约束响应体的类型，但是这个方法无法得到状态头信息
     *  .postForEntity 方法将返回ResponseEntity对象，这个方法的返回值不仅包含状态头信息还包含响应体
     *  .postForLocation 方法将返回jdk的URI对象
     *
     *
     */

    @GetMapping ("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){

        return  restTemplate.postForObject(PAYMENT_URL + "/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+ id,CommonResult.class);
    }


}





package com.sunflower.controller;

import com.sunflower.entities.CommonResult;
import com.sunflower.entities.Payment;
import com.sunflower.entities.ResultCode;
import com.sunflower.service.PaymentService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverport;


    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int Result = paymentService.create(payment);
        log.info("****** 插入结果 : "+ Result);
        if(Result>0){
            return new CommonResult(ResultCode.OK,"插入数据成功 ;端口号:"+serverport,Result);
        }else {
            return new CommonResult(ResultCode.ERROR,"插入数据失败",null);
        }

    }


    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("***#####**  查询结果 : "+ payment);
        if(payment!=null){
            return new CommonResult(ResultCode.OK,"查询成功 ;端口号:"+serverport, payment);
        }else {
            return new CommonResult(ResultCode.ERROR,"查询失败",null);
        }

    }

    @GetMapping(value = "/payment/lb")
    public String getPayment(){
        return serverport;
    }


}

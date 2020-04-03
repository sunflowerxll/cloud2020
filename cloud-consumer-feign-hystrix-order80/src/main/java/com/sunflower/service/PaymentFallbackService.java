package com.sunflower.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements  PaymentHystrixServer{

    @Override
    public String paymentInfo_ok(Integer id)
    {
        return "-----PaymentFallbackService fall back-paymentInfo_OK ,o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id)
    {
        return "-----PaymentFallbackService fall back-paymentInfo_TimeOut ,o(╥﹏╥)o";
    }

}

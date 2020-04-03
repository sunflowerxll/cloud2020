package com.sunflower.service.impl;


import com.sunflower.dao.PaymentDao;
import com.sunflower.entities.Payment;
import com.sunflower.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment){
        return paymentDao.create(payment);
    }

    public  Payment getPaymentById(Long id){
        return paymentDao.getPaymentById(id);
    }

}

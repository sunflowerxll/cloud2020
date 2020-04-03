package com.sunflower.service;


import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    /*********************************服务降级**********************************/

    public String paymentInfo_Ok(Integer id){
        return "线程池:" + Thread.currentThread().getName()+" paymentInfo_Ok ,id:" +id+"\t"+ "Haha...";
    }


    /**
     * 一旦调用服务方法失败,并抛错误后,会自动调用 @HystrixCommand标注好的fallbackMethod调用类中指定的方法.
     *
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value ="3000" )
    })
    public String paymentInfo_Timeout(Integer id){
        int timeoutMS =1000;
        try {
            TimeUnit.MILLISECONDS.sleep(timeoutMS);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "线程池:" + Thread.currentThread().getName()+" paymentInfo_timeout ,id:" +id+"\t"+ "耗时"+timeoutMS+"毫秒";
    }

    public String paymentInfo_TimeoutHandler(Integer id){

        return "线程池:" + Thread.currentThread().getName()+" StringpaymentInfo_TimeoutHandler ,id:" +id+"\t" +"o(╥﹏╥)o";
    }


    /********************************服务熔断***********************************/

    /**
     * Fallback: 控制HystrixCommand.getFallback() 如何执行
     * Circuit Breaker： 控制断路器的行为
     *      circuitBreaker.enabled  是否开启断路器功能
     *      circuitBreaker.requestVolumeThreshold   使断路器跳闸的最小请求数量
     *      circuitBreaker.sleepWindowInMilliseconds  断路器跳闸后,多久恢复(毫秒)
     *      circuitBreaker.errorThresholdPercentage   失败百分比率
     *      circuitBreaker.forceOpen        如果设置true，则强制使断路器跳闸
     *      circuitBreaker.forceClosed      如果设置true，则强制使断路器进行关闭状态
     */
    @HystrixCommand(fallbackMethod = "paymentCricuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")
    })


    /**
     *  服务熔断的例子,
     */
    public String paymentCircuitBreaker(@PathVariable Integer id){
        if(id<0){
            throw new RuntimeException("**** id不能为负数,id = "+id );
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+ ",调用流水号:"+serialNumber;
    }
    public  String paymentCricuitBreaker_fallback(@PathVariable Integer id){
        return "id不能为负数, 请稍后再试..... o(╥﹏╥)o id= "+id;
    }


}

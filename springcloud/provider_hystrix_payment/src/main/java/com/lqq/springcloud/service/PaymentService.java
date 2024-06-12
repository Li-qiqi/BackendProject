package com.lqq.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.commons.util.IdUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentOK(Long id) {
        return "线程池：" + Thread.currentThread().getName() + " paymentOK,id: " + id;
    }

    @HystrixCommand(fallbackMethod = "paymentTimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentTimeout(Long id) {
        try {

            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + " paymentTimeout,id: " + id;
    }

    // 降级fallbackMethod方法
    public String paymentTimeoutHandler(Long id){
        return "系统繁忙，请稍后再试！";
    }

    // 熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "ture"),   // 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),      //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),    //时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),    //失败率到达多少跳闸
    })
    public String paymentCircuitBreaker(Long id){

        if (id < 0) {
            throw new RuntimeException("id不能小于0");
        }
        String uuid = String.valueOf(UUID.randomUUID());
        return Thread.currentThread().getName() + ",流水号：" + uuid;
    }
    public String paymentCircuitBreakerFallback(Long id){
        return "paymentCircuitBreakerFallback";
    }
}

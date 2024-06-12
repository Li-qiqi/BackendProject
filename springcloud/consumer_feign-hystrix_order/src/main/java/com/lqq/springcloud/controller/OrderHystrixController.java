package com.lqq.springcloud.controller;

import com.lqq.springcloud.result.CommonResult;
import com.lqq.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/feign")
//@DefaultProperties(defaultFallback = "paymentFallbackMethod")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/hystrix/paymentOK/{id}")
//    @HystrixCommand
    public CommonResult paymentOK(@PathVariable("id") Long id){
//        int a = 10/0;
        return paymentHystrixService.paymentOK(id);
    }


    @GetMapping("/hystrix/paymentTimeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentTimeoutHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    })
    public CommonResult paymentTimeout(@PathVariable("id") Long id){
        return paymentHystrixService.paymentTimeout(id);
    }

    public CommonResult paymentTimeoutHandler(Long id){
        return new CommonResult(000,"系统繁忙，请稍后再试！88888");
    }

    // 全局fallbackMethod方法
    public CommonResult paymentFallbackMethod() {
        return new CommonResult(999,"对不起，出错了！");
    }
}

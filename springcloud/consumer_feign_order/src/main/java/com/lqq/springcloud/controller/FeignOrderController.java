package com.lqq.springcloud.controller;

import com.lqq.springcloud.entities.Payment;
import com.lqq.springcloud.result.CommonResult;
import com.lqq.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/feign")
public class FeignOrderController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/getPaymentById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){

        return paymentFeignService.paymentGetPaymentById(id);
    }
}

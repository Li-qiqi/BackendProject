package com.lqq.springcloud.controller;

import com.lqq.springcloud.result.CommonResult;
import com.lqq.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/hystrix")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/paymentOK/{id}")
    public CommonResult paymentOK(@PathVariable("id") Long id){
        String s = paymentService.paymentOK(id);
        log.info("paymentOk result id=" + id);
        return new CommonResult(200, "paymentOK", s);
    }

    @GetMapping("/paymentTimeout/{id}")
    public CommonResult paymentTimeout(@PathVariable("id") Long id) {
        String s = paymentService.paymentTimeout(id);
        log.info("paymentTimeout result: id=" + id);
        return new CommonResult(200, "paymentTimeout", s);
    }

    @GetMapping("/circuit/{id}")
    public CommonResult circuit(@PathVariable("id") Long id) {
        String s = paymentService.paymentCircuitBreaker(id);
        log.info("paymentTimeout result: id=" + id);
        return new CommonResult(200, "paymentTimeout", s);
    }
}

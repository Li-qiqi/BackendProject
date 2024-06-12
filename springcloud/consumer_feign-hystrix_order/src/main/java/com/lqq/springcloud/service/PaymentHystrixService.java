package com.lqq.springcloud.service;

import com.lqq.springcloud.fallback.PaymentFallback;
import com.lqq.springcloud.result.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallback.class)
public interface PaymentHystrixService {

    @GetMapping("/hystrix/paymentOK/{id}")
    public CommonResult paymentOK(@PathVariable("id") Long id);

    @GetMapping("/hystrix/paymentTimeout/{id}")
    public CommonResult paymentTimeout(@PathVariable("id") Long id);
}

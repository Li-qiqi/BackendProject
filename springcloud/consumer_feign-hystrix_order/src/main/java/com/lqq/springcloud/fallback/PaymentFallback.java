package com.lqq.springcloud.fallback;

import com.lqq.springcloud.result.CommonResult;
import com.lqq.springcloud.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallback implements PaymentHystrixService {
    @Override
    public CommonResult paymentOK(Long id) {
        return new CommonResult(777,"paymentOK");
    }

    @Override
    public CommonResult paymentTimeout(Long id) {
        return new CommonResult(999,"paymentTimeout");
    }
}

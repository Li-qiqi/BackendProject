package com.lqq.springcloud.controller;

import com.lqq.springcloud.entities.Payment;
import com.lqq.springcloud.result.CommonResult;
import com.lqq.springcloud.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);
    private static Logger crawlerLogger = LoggerFactory.getLogger("crawlerUser");

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/create")
    public CommonResult paymentCreate(@RequestBody Payment payment){
        log.info("payment create request: " + payment);
        int i = paymentService.create(payment);
        if (i > 0) {
            return new CommonResult(200, "server.port:"+ serverPort + ",success", null);
        } else {
            return new CommonResult(500,"server.port:"+ serverPort + ",添加数据失败，请稍后再试或者联系管理员");
        }
    }

    @GetMapping("/getPaymentById/{id}")
    public CommonResult paymentGetPaymentById(@PathVariable(value = "id") Long id){
        log.info("payment create getPaymentById request: id=" + id);
        Payment payment = paymentService.getPaymentById(id);
        log.info("payment create getPaymentById response: " + payment);
        if (payment != null) {
            return new CommonResult(200, "server.port:"+ serverPort + ",success", payment);
        } else {
            return new CommonResult(200, "server.port:"+ serverPort + ",查询数据为空");
        }
    }

    @GetMapping("/log")
    public void logTest(){
        crawlerLogger.info("log test");
    }
}

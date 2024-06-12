package com.lqq.springcloud.controller;

import com.lqq.springcloud.entities.Payment;
import com.lqq.springcloud.result.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {

    //    private static final String url = "http://localhost:8080";
    private static final String url = "http://PROVIDER-PAYMENT-SERVER";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/create")
    public CommonResult<Payment> create(Payment payment){
        log.info("order create request: " + payment);
        try {
            return restTemplate.postForObject(url + "/payment/create", payment, CommonResult.class);
        } catch (Exception e) {
            log.error("order create error" + e);
            return null;
        }
    }

    @GetMapping("/getPaymentById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        log.info("order getPaymentById request id: "+ id);
        return restTemplate.getForObject(url + "/payment/getPaymentById/"+id, CommonResult.class);
    }

    @GetMapping("/getService")
    public Object getService(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("*********" + service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("PROVIDER-PAYMENT-SERVER");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+instance.getHost()+instance.getPort()+instance.getUri());
        }
        return discoveryClient;
    }

    @GetMapping("/getPayment")
    public CommonResult<Payment> getPayment(){
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(url + "/payment/getPayment", CommonResult.class);

        if (forEntity.getStatusCode().is2xxSuccessful()) {
            return forEntity.getBody();
        } else {
            return new CommonResult<>(400, "查询结果失败");
        }
    }
}


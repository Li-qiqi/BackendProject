package com.lqq.springcloud.alibaba.service.impl;

import com.lqq.springcloud.alibaba.service.PaymentService;

import java.util.ArrayList;

public class PaymentServiceImpl implements PaymentService {


    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("dddd");
        String s = "";
        String[] split = s.split(",");
        System.out.println(strings.size());
        System.out.println(split.length);
    }
}

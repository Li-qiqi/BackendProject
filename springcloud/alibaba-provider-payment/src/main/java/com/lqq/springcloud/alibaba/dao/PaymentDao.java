package com.lqq.springcloud.alibaba.dao;

import com.lqq.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

    Payment getPayment(@Param("id") Integer paymentId);

}

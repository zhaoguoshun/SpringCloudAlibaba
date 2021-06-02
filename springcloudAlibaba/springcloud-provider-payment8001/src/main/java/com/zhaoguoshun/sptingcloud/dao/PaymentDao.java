package com.zhaoguoshun.sptingcloud.dao;

import com.zhaoguoshun.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PaymentDao {

    int create(Payment payment);

    Payment getPaymentById(@Param("id")Long id);
}

package com.zhaoguoshun.sptingcloud.service;

import com.zhaoguoshun.springcloud.entities.Payment;


public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(Long id);
}

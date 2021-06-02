package com.zhaoguoshun.sptingcloud.service.impl;

import com.zhaoguoshun.springcloud.entities.Payment;
import com.zhaoguoshun.sptingcloud.dao.PaymentDao;
import com.zhaoguoshun.sptingcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {


    private PaymentDao paymentDao;

    @Autowired
    public PaymentServiceImpl(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    public int create(Payment payment){
        return paymentDao.create(payment);
    }

    public Payment getPaymentById(Long id){
        return paymentDao.getPaymentById(id);
    }



}

package com.zhaoguoshun.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentHystrixServiceImpl implements PaymentHystrixService {


    @Override
    public String paymentInfoOK(Integer id) {
        return "---------------PaymentFallbackService fall back---------paymentInfoOK,哭";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "---------------PaymentFallbackService fall back---------paymentInfo_TimeOut,哭";
    }
}

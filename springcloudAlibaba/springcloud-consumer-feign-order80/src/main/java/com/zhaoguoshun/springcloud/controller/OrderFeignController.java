package com.zhaoguoshun.springcloud.controller;

import com.zhaoguoshun.springcloud.entities.CommonResult;
import com.zhaoguoshun.springcloud.entities.Payment;
import com.zhaoguoshun.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;


@RestController
@Slf4j
public class OrderFeignController {

    private PaymentFeignService paymentFeignService;

    @Autowired
    public OrderFeignController(PaymentFeignService paymentFeignService) {
        this.paymentFeignService = paymentFeignService;
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentId(@PathVariable(value = "id") Long id){
       return paymentFeignService.getPaymentId(id);
    }

    @GetMapping("/consumer/payment/feign/timeout")
    public String paymentTimeout(){
        return paymentFeignService.paymentFeignTimeout();
    }

}

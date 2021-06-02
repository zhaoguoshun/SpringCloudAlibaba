package com.zhaoguoshun.springcloud.service;

import com.zhaoguoshun.springcloud.entities.CommonResult;
import com.zhaoguoshun.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "SPRINGCLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping("/payment/get/{id}")
    CommonResult<Payment> getPaymentId(@PathVariable(value = "id") Long id);

    @GetMapping("/payment/feign/timeout")
    String paymentFeignTimeout();
}

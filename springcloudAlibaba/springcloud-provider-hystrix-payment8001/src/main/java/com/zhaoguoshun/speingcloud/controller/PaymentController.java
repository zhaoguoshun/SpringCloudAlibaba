package com.zhaoguoshun.speingcloud.controller;

import com.zhaoguoshun.speingcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {

    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfoOK(@PathVariable(value = "id") Integer id){
        log.info(paymentService.paymentInfo_OK(id));
        return paymentService.paymentInfo_OK(id);
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable(value = "id") Integer id){
        log.info(paymentService.paymentInfo_TimeOut(id));
        return paymentService.paymentInfo_TimeOut(id);
    }


    //======服务熔断

    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable(value = "id") Integer id){
        String s = paymentService.paymentCircuitBreaker(id);
        log.info(s);
        return s;
    }
}

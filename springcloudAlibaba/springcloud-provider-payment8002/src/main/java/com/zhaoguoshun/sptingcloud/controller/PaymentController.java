package com.zhaoguoshun.sptingcloud.controller;

import com.zhaoguoshun.springcloud.entities.CommonResult;
import com.zhaoguoshun.springcloud.entities.Payment;
import com.zhaoguoshun.sptingcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@Slf4j
public class PaymentController {

    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;


    private DiscoveryClient discoveryClient;

    @Autowired
    public PaymentController(PaymentService paymentService, DiscoveryClient discoveryClient) {
        this.paymentService = paymentService;
        this.discoveryClient = discoveryClient;
    }


    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        //
        int result = paymentService.create(payment);
        log.info("添加的结果集"+result);
        if (result>0){
            return new  CommonResult(200,"添加成功,serverPort="+serverPort,result);
        }else {
            return new  CommonResult(444,"添加失败",result);
        }
    }


    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentId(@PathVariable Long id){
        Payment result = paymentService.getPaymentById(id);
        log.info("查询的结果"+result+"哈哈哈哈哈");
        return new  CommonResult(200,"查询结果,serverPort="+serverPort,result);
    }

    @GetMapping("/payment/discovery")
    public Object  Discovery(){
        discoveryClient.getServices().stream().map(x->{log.info("ELEMENT"+x); return x;}).collect(Collectors.toList());
        discoveryClient.getInstances("SPRINGCLOUD-PAYMENT-SERVICE").stream().map(x->{
            log.info(x+"\t"+x.getHost()+"\t"+x.getPort()+"\t"+x.getUri());
            return x;
        }).collect(Collectors.toList());

        return this.discoveryClient;
    }

    @GetMapping("payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }
}

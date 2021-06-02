package com.zhaoguoshun.sptingcloud.controller;

import com.zhaoguoshun.springcloud.entities.CommonResult;
import com.zhaoguoshun.springcloud.entities.Payment;
import com.zhaoguoshun.sptingcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        //
        int result = paymentService.create(payment);
        log.info("添加的结果集"+result);
        if (result>0){
            return new  CommonResult(200,"添加成功,serverPort:"+serverPort,result);
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
    @GetMapping("payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e){
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping("/payment/zipkin")
    public String paymentZipkin()
    {
        return "hi ,i'am paymentzipkin server fall back，welcome to atguigu，O(∩_∩)O哈哈~";
    }


}

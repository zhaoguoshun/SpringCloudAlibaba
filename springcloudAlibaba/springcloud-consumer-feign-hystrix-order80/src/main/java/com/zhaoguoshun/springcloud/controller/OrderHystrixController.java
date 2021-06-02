package com.zhaoguoshun.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zhaoguoshun.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {

    private PaymentHystrixService paymentHystrixService;

    @Autowired
    public OrderHystrixController(PaymentHystrixService paymentHystrixService) {
        this.paymentHystrixService = paymentHystrixService;
    }

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        return paymentHystrixService.paymentInfoOK(id);
    }


    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "payInfoTimeOutFallbackMethod",commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1500")
//    })
    @HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable(value = "id") Integer id){
        int a=10/0;
        log.info(paymentHystrixService.paymentInfo_TimeOut(id));
        return paymentHystrixService.paymentInfo_TimeOut(id);
    }

    public String payInfoTimeOutFallbackMethod(@PathVariable(value = "id") Integer id){
        return "我是消费者80：对方支付系统繁忙请10秒钟再试或者运行错误请检查自己 o(╥﹏╥)oo(╥﹏╥)o";
    }


    //下面是全局fallback
    public String payment_Global_FallbackMethod(){
        return "我是Global再试或者运行错误请检查自己 o(╥﹏╥)oo(╥﹏╥)o";
    }

}

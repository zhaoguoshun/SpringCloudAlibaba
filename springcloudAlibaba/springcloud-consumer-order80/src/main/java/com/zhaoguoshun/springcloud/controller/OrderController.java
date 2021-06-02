package com.zhaoguoshun.springcloud.controller;

import com.zhaoguoshun.springcloud.entities.CommonResult;
import com.zhaoguoshun.springcloud.entities.Payment;
import com.zhaoguoshun.springcloud.lb.LoadBalance;
import com.zhaoguoshun.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {


    public static final String PAYMENT_URL="http://SPRINGCLOUD-PAYMENT-SERVICE";

    private RestTemplate restTemplate;

    private LoadBalance loadBalance;
    @Autowired
    private LoadBalancer loadBalancer;

    private DiscoveryClient discoveryClient;

    @Autowired
    public OrderController(RestTemplate restTemplate, LoadBalance loadBalance, DiscoveryClient discoveryClient) {
        this.restTemplate = restTemplate;
        this.loadBalance = loadBalance;
        this.discoveryClient = discoveryClient;
    }

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable Long id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable Long id){

        ResponseEntity<CommonResult> result = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);

        if (result.getStatusCode().is2xxSuccessful()){
            log.info("statusCode====>"+result.getStatusCode());
            return result.getBody();
        }else {
            return  new CommonResult<>(444,"操作失败");
        }
    }

    @GetMapping("/consumer/forEntity/create")
    public CommonResult<Payment> ForEntityCreate(Payment payment){
        return restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, CommonResult.class).getBody();
    }

//    @GetMapping(value = "/consumer/payment/lb")
//    public String getPaymentLB(){
//        List<ServiceInstance> instances = discoveryClient.getInstances("SPRINGCLOUD-PAYMENT-SERVICE");
//        if (instances==null || instances.size()==0){
//            return null;
//        }
//        ServiceInstance serviceInstance=loadBalance.instances(instances);
//        URI uri = serviceInstance.getUri();
//        return restTemplate.getForObject(uri+"payment/lb",String.class);
//    }
    @GetMapping("/consumer/payment/lb")
    public String lb(){
        List<ServiceInstance> instances = discoveryClient.getInstances("SPRINGCLOUD-PAYMENT-SERVICE");

        if (instances==null){
            log.info("没找到对应的服务器");
            return null;
        }
        ServiceInstance instance = loadBalancer.getInstance(instances);
        return restTemplate.getForObject(instance.getUri()+"payment/lb",String.class);
    }

    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin()
    {
        String result = restTemplate.getForObject("http://localhost:8001"+"/payment/zipkin/", String.class);
        return result;
    }

}

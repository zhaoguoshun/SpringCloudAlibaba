package com.zhaoguoshun.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class NacosOrderController {

    @Value("${service-url.nacos-user-service}")
    private String SERVER_URL;

    private RestTemplate restTemplate;

    @Autowired
    public NacosOrderController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

   @GetMapping("/consumer/payment/nacos/{id}")
   public String getPayment(@PathVariable(value = "id") Long id){
        return restTemplate.getForObject(SERVER_URL+"/payment/nacos/"+id,String.class);
   }

}


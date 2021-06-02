package com.zhaoguoshun.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {


    @GetMapping("/testA")
    public String testA(){
        System.out.println("TestA");
        return "---------------TestA";
    }

    @GetMapping("/testB")
    public String testB(){
        System.out.println("testB");
        return "---------------testB";
    }

    @GetMapping("/testC")
    public String testC(){
        System.out.println("testC");
        try {
           Thread thread= new Thread();
           thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "---------------testC";
    }

    @GetMapping("/testD")
    public String testD(){
        System.out.println("testD 异常比例");
        int age=10/0;
        return "---------------testD";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value ="p1",required = false) String p1,
                             @RequestParam(value ="p2",required = false) String p2){
        return "----------------testHotKey";
    }


    public String deal_testHotKey(String p1, String p2, BlockException exception){
        return "------------deal_testHotKey";
    }
}

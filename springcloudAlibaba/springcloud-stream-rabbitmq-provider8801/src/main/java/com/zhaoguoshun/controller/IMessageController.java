package com.zhaoguoshun.controller;

import com.zhaoguoshun.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IMessageController {

    private IMessageProvider iMessageProvider;

    @Autowired
    public IMessageController(IMessageProvider iMessageProvider) {
        this.iMessageProvider = iMessageProvider;
    }


    @GetMapping("/send")
    public String send(){
        return iMessageProvider.send();
    }
}

package com.zhaoguoshun.service.impl;

import com.zhaoguoshun.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

@EnableBinding(Source.class) //定义消息的推送管道
public class IMessageProviderImpl implements IMessageProvider {


    private MessageChannel output; //消息管道

    @Autowired
    public IMessageProviderImpl(MessageChannel output) {
        this.output = output;
    }

    @Override
    public String send() {

        String uuid= UUID.randomUUID().toString();

        output.send(MessageBuilder.withPayload(uuid).build());
        System.out.println("**************UUID:"+uuid);
        return null;
    }
}

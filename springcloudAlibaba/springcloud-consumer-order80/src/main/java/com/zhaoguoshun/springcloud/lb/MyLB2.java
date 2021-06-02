package com.zhaoguoshun.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB2 implements LoadBalancer {

    private AtomicInteger atomicInteger=new AtomicInteger();


    private final int install(){
        int current;
        int next;
        do {
            current=atomicInteger.get();
            next=current>=2456821 ? 0 :current+1;
        }while (!this.atomicInteger.compareAndSet(current,next));
        return next;
    }

    @Override
    public ServiceInstance getInstance(List<ServiceInstance> serviceInstances) {
        int index=install() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}

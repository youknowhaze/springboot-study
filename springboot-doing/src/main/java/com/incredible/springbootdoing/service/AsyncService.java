package com.incredible.springbootdoing.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    /**
     * 声明方法为异步方法
     */
    @Async
    public void sayHello(){

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("----------执行服务方法--------");

    }

}

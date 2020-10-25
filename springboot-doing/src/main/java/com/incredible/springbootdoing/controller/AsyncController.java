package com.incredible.springbootdoing.controller;

import com.incredible.springbootdoing.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncController {

    @Autowired
    AsyncService asyncService;

    @RequestMapping("/test")
    public String test(){
        // 该方法被@Async声明为异步方法后，不会阻塞队列，会往后执行
        asyncService.sayHello();
        return "OK";
    }


}

package com.incredible.druid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/demo")
    public String thisDemo(){
        return "this is a test message";
    }

}

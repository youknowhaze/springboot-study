package com.incredible.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/logintest")
    public String login(){
        return "success";
    }

    @RequestMapping("/mylogin")
    public String mylogin(){
        return "mylogin";
    }

    @RequestMapping("test01")
    public String test01(){
        return "test01";
    }

    @RequestMapping("test02")
    public String test02(){
        return "test02";
    }

    @RequestMapping("test03")
    public String test03(){
        return "test03";
    }

}

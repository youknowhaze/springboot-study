package com.incredible.controller;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TestController {

    @RequestMapping("/index")
    public String toindex(Model model){

        model.addAttribute("msg","this is test message");
        return "index";
    }

    @RequestMapping("/user/test1")
    public String totest1(){
        return "user/test1";
    }

    @RequestMapping("/user/test2")
    public String totest2(){
        return "user/test2";
    }


}

package com.incredible.spring_aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @Autowired
    @Qualifier("userServiceimpl")
    private UserServiceimpl userServiceimpl;

    @RequestMapping("/test")
    public String test() throws Exception {
        //userServiceimpl.saveUser("");
        userServiceimpl.saveUser("testStr");
        return "this is a test message";
    }

}

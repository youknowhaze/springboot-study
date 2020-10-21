package com.incredible.spring_aop;


import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service("userServiceimpl")
public class UserServiceimpl {

    public void saveUser(String str) throws Exception {
        if (StringUtils.isEmpty(str)){
            throw new Exception("抛出自定义异常.....");
        }
    }

}

package com.incredible.springbootdoing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SpringbootDoingApplicationTests {

    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void emailTest() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom("458051205@qq.com");
        simpleMailMessage.setSubject("springboot 邮件发送功能测试邮件");
        simpleMailMessage.setText("this is a springboot send mail test message");
        simpleMailMessage.setTo("458051205@qq.com","326877062@qq.com");

        javaMailSender.send(simpleMailMessage);
    }

    @Test
    void redisTest(){
        // opsForValue() 方法是操作字符串的，还有opsForList()/opsForSet()
        redisTemplate.opsForValue().set("incredible-test","this is a test message");
        System.out.println(redisTemplate.opsForValue().get("incredible-test"));

    }

}

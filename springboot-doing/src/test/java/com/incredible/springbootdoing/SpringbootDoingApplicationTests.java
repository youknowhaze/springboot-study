package com.incredible.springbootdoing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootTest
class SpringbootDoingApplicationTests {

    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Test
    void contextLoads() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom("458051205@qq.com");
        simpleMailMessage.setSubject("springboot 邮件发送功能测试邮件");
        simpleMailMessage.setText("this is a springboot send mail test message");
        simpleMailMessage.setTo("458051205@qq.com","326877062@qq.com");

        javaMailSender.send(simpleMailMessage);
    }

}

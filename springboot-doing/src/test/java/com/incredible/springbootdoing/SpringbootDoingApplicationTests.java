package com.incredible.springbootdoing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;


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
    void emailTest2() throws MessagingException {
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
        // 组装,设置能否添加参数为true

        MimeMessageHelper helper = new MimeMessageHelper(mimeMailMessage,true);
        helper.setFrom("458051205@qq.com");
        helper.setSubject("springboot email test");
        helper.setText("this is a test message");
        // 添加附件
        helper.addAttachment("1.jpg", new File("C:\\Users\\incredible\\Desktop\\1.jpg"));
        helper.addAttachment("2.jpg", new File("C:\\Users\\incredible\\Desktop\\1.jpg"));

        helper.setTo("326877062@qq.com");
        javaMailSender.send(mimeMailMessage);
    }


    @Test
    void redisTest(){
        // opsForValue() 方法是操作字符串的，还有opsForList()/opsForSet()
        redisTemplate.opsForValue().set("incredible-test","this is a test message");
        System.out.println(redisTemplate.opsForValue().get("incredible-test"));

    }

}

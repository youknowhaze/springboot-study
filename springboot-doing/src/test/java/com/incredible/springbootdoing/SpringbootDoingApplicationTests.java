package com.incredible.springbootdoing;

import org.apache.tomcat.jni.File;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@SpringBootTest
class SpringbootDoingApplicationTests {

    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Qualifier("incredibleRedis")
    @Autowired
    RedisTemplate redisTemplate;

    /*
      stringRedisTemplete继承了RedisTemplate，在它的构造器中声明并指定了序列化器.
      且它指定了redisTemplete的类型为<String,String>
     */
    @Autowired
    StringRedisTemplate stringRedisTemplate;

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
   /*     helper.addAttachment("1.jpg", new File("C:\\Users\\incredible\\Desktop\\1.jpg"));
        helper.addAttachment("2.jpg", new File("C:\\Users\\incredible\\Desktop\\1.jpg"));*/

        helper.setTo("326877062@qq.com");
        javaMailSender.send(mimeMailMessage);
    }


    @Test
    void redisTest(){
        redisTemplate.opsForValue().set("key1","value1");

        redisTemplate.opsForValue().set("int_key","1");
        stringRedisTemplate.opsForValue().set("int","1");
        // redisTemplate本身默认使用的jdk的序列化器，无法进行计算，重写序列化器后的templete是可以进行计算的
        //Long result0 = redisTemplate.opsForValue().increment("int_key",10);
        Long result = stringRedisTemplate.opsForValue().increment("int",10);
        System.out.println(" ----------- :"+result);



    }








}

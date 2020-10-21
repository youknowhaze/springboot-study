package com.incredible.spring_IoC.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ＠Configuration 代表这是一个 Java 配置文件 Spring 的容器会根据
 * 它来生成 IoC 容器去装配 Bean; @Bean 代表将 initUser 方法返回的 POJO 装配到 IoC 容器中，而其
 * 属性 name 定义这个 Bean的名称，如果没有配置它，则将方法名称“initUser ”作为 Bean 的名称保
 * 存到 Spring IoC 容器中
 */
@Configuration
public class AppConfig {
    @Bean(name = "MyUser")
    public User initUser(){
        User user = new User();
        user.setId(12L);
        user.setName("incredible");
        user.setNote("this is a test message");
        return user;
    }
}

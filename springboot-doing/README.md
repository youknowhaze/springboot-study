# springboot-study

## 一、线程异步
### 1、开启异步，在启动类配置**@EnableAsync**开启异步功能
### 2、在需要异步执行的方法，方法体上加上**@Async**注释，声明为异步方法即可


## 二、邮件发送功能

### 1、引入邮件依赖
```java
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-mail</artifactId>
        </dependency>
```
依赖导入成功即可查看到邮件的自动装配情况  **MailSenderAutoConfiguration**

### 2、在yaml中配置邮件参数
````java
spring:
  mail:
    username: 458051205@qq.com
    password: xntufimkwilibibf
    host: smtp.qq.com
    default-encoding: UTF-8
    properties:
      mail.smtp.ssl.enable: true
````
### 3、代码中注入**JavaMailSenderImpl**进行使用即可
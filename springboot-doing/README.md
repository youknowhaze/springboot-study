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
```java
 @Autowired
    JavaMailSenderImpl javaMailSender;

    @Test
    void emailTest() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom("458051205@qq.com");
        simpleMailMessage.setSubject("springboot 邮件发送功能测试邮件");
        simpleMailMessage.setText("this is a springboot send mail test message");
        simpleMailMessage.setTo("458051205@qq.com","326877062@qq.com");

        javaMailSender.send(simpleMailMessage);
    }
```

## 三、springboot整合redis

### 1、导入redis依赖
```java
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-pool2</artifactId>
        </dependency>
```

**说明：**
1、从springboot2.x之后，springboot整合redis的底层就不再建议使用jedis了，现在使用的是lettuce
2、因为线程安全的要求，一般使用redis都是需要使用pool的，而不会直接使用redis连接

### 2、redis连接配置
**在yaml配置文件中对redis进行如下基本配置**
````java
spring:
  redis:
    host: 192.168.106.128
    port: 6379
    password: redis123
    lettuce:
      pool:
        max-active: 50
````
**在配置pool的时候，注意使用lettuce进行配置，jedis配置不再生效**

### 3、redis的基本使用
直接依赖注入 **RedisTemlate** ，
```java
    @Autowired
    RedisTemplate redisTemplate;

@Test
    void redisTest(){
        // opsForValue() 方法是操作字符串的，还有opsForList()/opsForSet()
        redisTemplate.opsForValue().set("incredible-test","this is a test message");
        System.out.println(redisTemplate.opsForValue().get("incredible-test"));
        
    }
```
当自定义一个配置类，命名一个bean为**redisTemplate**时，会覆盖soringboot默认装配的redisTemplate

### 4、重写依赖
````java
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        //设置序列化器

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer =
                new Jackson2JsonRedisSerializer(Object.class);

        // jackSon所带的序列化器
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL,JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance ,
                ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 字符序列化器
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);

        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

        return redisTemplate;
    }
}
````

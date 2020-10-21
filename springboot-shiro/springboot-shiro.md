# springboot-shiro 

### 一、shiro快速入门

#### 1、shiro 是apache提供的一个安全权限验证框架，它的官网提供了10分钟快速入门的案例，地址如下
````
http://shiro.apache.org/10-minute-tutorial.html
````

#### 2、需要下载官方提供的案例进行学习分析，github地址如下：
````$xslt
https://github.com/apache/shiro
````
#### 3、这里我是直接下载的压缩包，对压缩包进行解压
目录下的**shiro-master\samples\quickstart** 文件夹即是快速入门示例

#### 4、maven项目的shiro入门主要由3个部分组成
 - maven依赖
 - shiro.ini配置文件
 - shiro配置类
 
###  二、springboot整合shiro

#### 1、同样的，官方提供了demo示例，在下载的目录下 **shiro-master\samples\springboot** 和 **shiro-master\samples\springboot-web**

#### 2、整合步骤

 - 导入maven依赖，如下,为了方便测试，还导入了thymeleaf：
 ````$xslt
<dependency>
  <groupId>org.apache.shiro</groupId>
    <artifactId>shiro-spring</artifactId>
    <version>1.5.3</version>
</dependency>

<dependency>
     <groupId>org.thymeleaf</groupId>
     <artifactId>thymeleaf-spring5</artifactId>
 </dependency>

````

 - 创建配置类 
   · 创建shiro配置类ShiroConfig,用@Configuration申明为配置类，主要有三个方法,从下往上创建，先创建UserRealm实体
````java
 // ShiroFilterFactoryBean
   
    //DefaultWebSecurityManager
   
    //创建UserRealm对象

````
   · 创建一个UserRealm授权类，名称可以自定义，这个类必须继承AuthorizingRealm类
   · 重写授权和认证的方法,这是两个重载的方法，参数类型不一致
```java
public class UserRealm extends AuthorizingRealm {
    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("==============>执行了授权");
        return null;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("==============>执行了认证");
        return null;
    }
}
```
  ·再依次实现创建DefaultWebSecurityManager和ShiroFilterFactoryBean的方法，并注册为bean
````java
 // shirofilterfactorybean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier(value = "securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        return bean;
    }


    //defualtwebsecuritymanager
    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier(value = "userRealm") UserRealm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }


    //创建realm对象

    @Bean(name = "userRealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }
````




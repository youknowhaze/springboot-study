package com.incredible.spring_aop;

import org.aspectj.lang.annotation.*;

/**
 * 定义切面
 * execution(* com.incredible.springbootweb.spring_aop.TestController.test(..)
 * 1、execution表示执行的时候拦截括号内的方法
 * 2、* 表示任意返回类型
 * 3、com.incredible.springbootweb.spring_aop.TestController  表示对象的全限定类名
 * 4、test 表示指定对象的方法
 * 5、(..)表示任意参数进行匹配
 *
 * 还可以 com.incredible.springbootweb.spring_aop.*.test(..) 匹配spring_aop包下的所有类的test方法
 * 切面不仅仅是只能切controller层，service和其他层也是可以切
 */
@Aspect
public class SpringRequestAop {

    @Pointcut("execution(* com.incredible.springbootweb.spring_aop.UserServiceimpl.saveUser(..)")
    public void pointAop(){}


    /**
     * 使用了注解＠Pointcut 来定义切点 ，它标注在方法 pointCut 上，则在后面的通知注解中
     * 就可以使用方法名称来定义了
     */
    @Around("pointAop()")
    public void around(){
        System.out.println("==========进入切面========");
    }


    @Before("pointAop()")
    public void beforeAop(){
        System.out.println("========执行切面before方法=======");
    }


    @After("pointAop()")
    public void afterAop(){
        System.out.println("========执行切面After方法=======");
    }


    /**
     * 若TestController.test() 调用方法中传递参数不为空，则不会抛出异常，执行此方法
     */
    @AfterReturning("pointAop()")
    public void afterReturningAop(){
        System.out.println("========执行切面AfterReturning方法=======");
    }

    /**
     * 若TestController.test() 调用方法中传递参数为空，则会抛出异常，执行此方法
     */
    @AfterThrowing("pointAop()")
    public void afterThrowingAop(){
        System.out.println("========执行切面AfterThrowing方法=======");
    }


}

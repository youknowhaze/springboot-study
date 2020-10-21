package com.incredible.spring_IoC.LifeCycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class BussinessPerson implements Person, BeanNameAware,
        BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {

    private Animal animal;

    @Override
    public void doServcie() {
        animal.used();
    }

    @Override
    public void SetAnimal(@Autowired @Qualifier("cat") Animal animal) {
        this.animal = animal;
    }

    @Override
    public void setBeanName (String beanName ) {
        System.out.println("---------- 调用了BeanNameAware的 setBeanName() 方法");
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("----------- 调用了BeanFactoryAware的 setBeanFactory() 方法");
    }

    @PostConstruct
    public void init(){
        System.out.println("============  调用了自定义的初始化方法 init()");
    }

    @PreDestroy
    public void myDestroy(){
        System.out.println("============= 调用了自定义的摧毁方法 myDestroy()");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("------------ 调用了DisposableBean的 destroy() 方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("----------- 调用了InitializingBean的 afterPropertiesSet() 方法");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("-------------  调用了ApplicationContextAware的 setApplicationContext() 方法");
    }
}

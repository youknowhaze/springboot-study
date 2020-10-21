package com.incredible.spring_IoC.LifeCycle;

import org.springframework.stereotype.Component;

@Component("dog")
public class Dog implements Animal{
    @Override
    public void used() {
        System.out.println("【狗】正在看门，"+this.getClass().getName());
    }
}

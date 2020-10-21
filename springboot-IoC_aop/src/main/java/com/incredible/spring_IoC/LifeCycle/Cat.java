package com.incredible.spring_IoC.LifeCycle;

import org.springframework.stereotype.Component;

@Component("cat")
public class Cat implements Animal{
    @Override
    public void used() {
        System.out.println("【猫】抓获了一只老鼠，"+this.getClass().getName());
    }
}

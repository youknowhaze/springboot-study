package com.incredible.spring_IoC.springEL;

import org.springframework.beans.factory.annotation.Value;

/**
 * 部分spring EL语法的使用
 */
public class SpringELAssignment {

    @Value("#{'使用 Spring EL 赋值字符串'}")
    private String name;

    @Value ("#{'username' eq 'testname'}")
    private boolean flag;

    @Value("#{3.14}")
    private float num;

}

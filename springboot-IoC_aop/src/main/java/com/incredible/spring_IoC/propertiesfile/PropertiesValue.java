package com.incredible.spring_IoC.propertiesfile;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesValue {

    @Value("${spring.data.test}")
    private String testName;
    @Value("${spring.data.demo}")
    private String demoName;


}

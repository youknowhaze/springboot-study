package com.incredible.spring_IoC.propertiesfile;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 直接注解spring.data下的内容至类的成员中，需主要，必须有set方法才能注入成功
 */
@ConfigurationProperties("spring.data")
@Setter
@Getter
public class PropertiesConfiguration {
    private String demoName;
    private String testName;

    @Override
    public String toString(){
        return demoName + " ------ " + testName;
    }
}

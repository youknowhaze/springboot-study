package com.incredible.spring_IoC.scan;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration 将当前类标注为配置类，而ComponentScan表明了他会进行扫描，
 * 但只会扫描这个类同级目录及子目录下的spring bean对象
 */
@Configuration
@ComponentScan
public class AppConfigScan {
}


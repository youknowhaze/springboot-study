package com.incredible.spring_IoC.scanFilter;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

/**
 * Configuration 将当前类标注为配置类，而ComponentScan表明了他会进行扫描，
 * 但只会扫描这个类同级目录及子目录下的spring bean对象,这里因为和userScanFilter同级
 * 的还有一个类所以需要进行指定或者过滤
 * 1、 扫描指定目录bean加载至容器
 *     @ComponentScan (”com.incredible.springbootweb.spring_IoC.*”) 或
 *     @ComponentScan (basePackages = {”com.incredible.springbootweb.spring_IoC.scanFilter”})
 *
 * 2、扫描指定的bean
 *     @ComponentScan(basePackageClasses = {UserScanFilter.class})
 *
 * 3、扫描指定包下的bean对象，排除某些bean(这里的意思是排除了指定包下所有使用@Service注解的bean)
 *     @ComponentScan (basePackages = ”com.incredible.springbootweb.spring_IoC.scanFilter” , excludeFilters = {@Filter(classes = {Service.class})}))
 *
 * 为了进行练习，这里就使用指定加排除的方法好了
 */
@Configuration
/**
 *  扫描com.incredible.springbootweb.spring_IoC.scanFilter包下的所有bean，
 *  excludeFilters表示排除@service注解
 *  还有一个 includeFilters注解表示满足条件的才加载扫描、
 */

@ComponentScan (basePackages = "com.incredible.springbootweb.spring_IoC.scanFilter",excludeFilters = {@ComponentScan.Filter(classes = {Service.class})})
public class AppConfigScanFilter {

}


package com.incredible.spring_IoC.profileEnv;

import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * spring中存在2个可以修改profile机制的配置
 * spring.profiles.default 和 spring.profiles.active
 * 若这两个配置都不存在，则spring不会将@Profile标注的Bean加载到spring IoC，
 * spring 是先判断是否存在active的配置再去找default的配置，因此active的优先级高于default
 * 在java启动项目中，配置如下配置就能启动 Profile 机制
 * JAVA_OPTS= "-Dspring.profiles.active=pre"
 */
@Component
public class ProfileDataSource {

    @Bean(name = "dataSource")
    @Profile(value = "test")
    public DataSource getTestDataSource(){
        Properties properties = new Properties();
        properties.setProperty("driver", "com.mysql.jdbc.Driver");
        properties.setProperty("url", "jdbc:mysql://localhost:3306/test");
        properties.setProperty("username", "root");
        properties.setProperty("password", "root");
        DataSource dataSource = null;
        try {
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }


    @Bean(name = "dataSource")
    @Profile(value = "pre")
    public DataSource getPreDataSource(){
        Properties properties = new Properties();
        properties.setProperty("driver", "com.mysql.jdbc.Driver");
        properties.setProperty("url", "jdbc:mysql://localhost:3306/pre");
        properties.setProperty("username", "root");
        properties.setProperty("password", "root");
        DataSource dataSource = null;
        try {
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }


}

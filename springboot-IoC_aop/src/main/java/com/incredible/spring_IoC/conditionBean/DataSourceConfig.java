package com.incredible.spring_IoC.conditionBean;

import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 条件装配bean，@Conditional(DataSourceCondition.class) 满足DataSourceCondition的条件才会被加载为bean。
 * DataSourceCondition 必须实现matches方法
 */
@Component
public class DataSourceConfig {


    @Bean("conditionDataSourceBean")
    @Conditional(DataSourceCondition.class)
    public DataSource initDataSouce(@Value("${spring.dataSource.driver}") String driver,@Value("${spring.dataSource.url}") String url,
                                    @Value("${spring.dataSource.username}") String username,@Value("${spring.dataSource.password}") String password){
        Properties properties = new Properties();
        properties.setProperty("driver", driver);
        properties.setProperty("url", url);
        properties.setProperty("username", username);
        properties.setProperty("password", password);
        DataSource dataSource = null;
        try {
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }

}

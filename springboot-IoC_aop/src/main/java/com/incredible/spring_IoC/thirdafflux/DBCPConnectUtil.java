package com.incredible.spring_IoC.thirdafflux;

import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * dbcp2 连接生成
 */
@Component
public class DBCPConnectUtil {

    /**
     * 生成dbcp2的连接方法，注入至bean中
     * @return
     */
    @Bean(name = "dataSource")
    public DataSource getDataSource () {

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
}

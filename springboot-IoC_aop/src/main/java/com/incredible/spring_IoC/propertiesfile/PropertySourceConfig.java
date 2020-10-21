package com.incredible.spring_IoC.propertiesfile;


import org.springframework.context.annotation.PropertySource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 指定加载某一个配置文件
 */
@PropertySource(value={"classpath:dev/db.properties"})
public class PropertySourceConfig {

    private static Properties p;

    private static void initProperties() {
        InputStream inputStream = null;
        try {
            inputStream = PropertySourceConfig.class.getClassLoader().getResourceAsStream("db.properties");
            p.load(inputStream);
        } catch (FileNotFoundException e1) {
            e1.getMessage();
        } catch (IOException e) {
            e.getMessage();
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.getMessage();
                }
            }
        }
    }


    public static String getPramValue(){
       return p.getProperty("username");
    }

}

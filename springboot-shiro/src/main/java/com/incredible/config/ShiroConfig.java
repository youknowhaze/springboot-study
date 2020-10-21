package com.incredible.config;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.ldap.DefaultLdapRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {

    // shirofilterfactorybean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier(value = "securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        return bean;
    }


    //defualtwebsecuritymanager
    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier(value = "userRealm") UserRealm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }


    //创建realm对象

    @Bean(name = "userRealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }


}

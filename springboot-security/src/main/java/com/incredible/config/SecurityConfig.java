package com.incredible.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

/**
 * 配置security的配置类
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 授权
     * @param http
     * @throws Exception
     */
    /*@Autowired
    private DataSource dataSource;*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 首页所有人可以访问，功能页只有拥有对应权限的角色能够访问
        // 请求授权的规则
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/test01").hasAnyRole("role01","root")
                .antMatchers("/test02").hasRole("role02")
                .antMatchers("/test03").hasAnyRole("role3","root");
        //没有权限跳转到login页面
        //http.formLogin();
        //指定登录页面,指定用户名密码参数命名
        http.formLogin().loginPage("/mylogin").usernameParameter("user").passwordParameter("pwd").successForwardUrl("/login");
        // 关闭跨站请求攻击
        http.csrf();
        http.logout().logoutUrl("/index");
        // 记住我功能，默认保存2周
        http.rememberMe();
    }

    /**
     * 认证
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 基于内存验证
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("root").and()
                .withUser("guofei").password(new BCryptPasswordEncoder().encode("123456")).roles("role02").and()
                .withUser("testuser").password(new BCryptPasswordEncoder().encode("123456")).roles("role01");
        //基于数据库查询验证
        /*auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("root").and()
                .withUser("guofei").password(new BCryptPasswordEncoder().encode("123456")).roles("role02").and()
                .withUser("testuser").password(new BCryptPasswordEncoder().encode("123456")).roles("role01");
 */
    }
}

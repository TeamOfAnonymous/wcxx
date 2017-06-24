package com.anonymous.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by huangMP on 2017/6/23.
 * decription : Security 配置文件
 */
@EnableWebSecurity
public class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    // 静态资源访问的 url
    private String[] staticFileUrl = {
            "/css/**","**.css",
            "/fonts/**",
            "/img/**",
            "**.js","/js/**",
            "/scss/**",
            "/url/**",
            "/vendor/**"};
    // 不用认证就可访问的 url
    private String[] permitUrl = {"/"};

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(staticFileUrl);
        web.ignoring().antMatchers(permitUrl);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 取消避免点击劫持 (clickjacking) 的攻击
        http.headers().frameOptions().sameOrigin();

        // 取消跨区请求攻击
        http.csrf().disable();

        // 访问url认证
        http
                .authorizeRequests()
                .anyRequest().authenticated();

        // 配置登陆信息
        http
                .formLogin()
                .loginPage("/goLogin")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/goHeader")
                .permitAll()
                .and();

        // 配置退出登陆信息
        http
                .logout()
                .logoutSuccessUrl("/goLogin")
                .invalidateHttpSession(true)
                .deleteCookies()
                .and();

        http.httpBasic();
    }
}
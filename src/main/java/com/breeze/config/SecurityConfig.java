package com.breeze.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author tx*/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http   //放行所有权限
                .authorizeRequests().antMatchers("/**").permitAll()
                .and()
                .csrf().disable();

        //  < scruty: csrf enabled=false/>

    }
}

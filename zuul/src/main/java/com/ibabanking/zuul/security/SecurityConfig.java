package com.ibabanking.zuul.security;

import com.ibabanking.zuul.config.JwtConfig;
import com.ibabanking.zuul.filter.JwtFilter;
import com.ibabanking.zuul.jwt.JwtVerifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtConfig jwtConfig;

    public SecurityConfig(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtVerifier(jwtConfig), JwtFilter.class)
                .authorizeRequests()
                .anyRequest().authenticated();
    }
}

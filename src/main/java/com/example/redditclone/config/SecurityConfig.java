package com.example.redditclone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity //Read Later
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable() //can only happen when application uses cookies and session. APIs are stateless; therefore disabling it
                .authorizeRequests()
                .antMatchers("/api/auth/**")//Any other request other than the mentioned request should be authenticated.
                .permitAll()
                .anyRequest()
                .authenticated();
    }
    @Bean //Read Later + why no public field
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}

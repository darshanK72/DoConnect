package com.doconnect.doconnectservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.doconnect.doconnectservice.services.CustomUserDetailService;

/*
 * @Author : Darshan Khairnar
 * Created Date : 25-8-2022
 * Modified Date : 26-8-2022
 * Description : Web Security Config class, use for configurations and security
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
    prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    CustomUserDetailService customUserDetailService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
            .authorizeRequests()
            .antMatchers("/doconnect/question/getAllApprovedAnswersOfQuestions/**").permitAll()
            .antMatchers("/doconnect/question/getAllQuestionByQuery/**").permitAll()
            .antMatchers("/doconnect/question/getAllApprovedQuestions").permitAll()
            // .antMatchers("/question/getAllApprovedAnswersOfQuestions").permitAll()
            .antMatchers("/doconnect/answer/getall").permitAll()
            .antMatchers("/doconnect/user/register").permitAll()
            .antMatchers("/doconnect/login").permitAll()
            .antMatchers("/doconnect/user/get").hasRole("ADMIN")
            .anyRequest().authenticated().and().httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService);
    }

    

    
}

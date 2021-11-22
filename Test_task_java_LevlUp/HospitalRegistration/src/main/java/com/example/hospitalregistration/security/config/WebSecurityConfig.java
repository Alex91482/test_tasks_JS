package com.example.hospitalregistration.security.config;

import com.example.hospitalregistration.security.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception { //дефолтовый юзер для регистрации  Your login: 90898083 Your password: gJHMERmb

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/patientHome").hasRole("PATIENT")
                .antMatchers("/**", "/doctors","/doctorNameTimetable/**","/doctorTimetable/**",
                        "/registration/**","/getRecord/**","/pageMailMessage","/login", "/logout","/home/**"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                //Настройка для входа в систему
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/patientHome")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/");

    }
}


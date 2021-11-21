package com.example.hospitalregistration.security.config;

import com.example.hospitalregistration.security.UserDetailsServiceImpl;
import com.example.hospitalregistration.security.jsypt.JasyptUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    JasyptUtil jasyptUtil;

    //@Value("${encrypted.option}")
    //private String property;
    private static final String property = "ENC(uTSqb9grs1+vUv3iN8lItC0kl65lMG+8)";

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return jasyptUtil.decyptPwd(property, charSequence.toString());
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return jasyptUtil.decyptPwd(property, charSequence.toString()).equals(s);
            }
        };
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        //аккаунты пациентов
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {



        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/**", "/doctors","/doctorNameTimetable/**","/doctorTimetable/**",
                        "/registration/**","/getRecord/**","/pageMailMessage","/login", "/logout","/home/**"
                ).permitAll()
                .anyRequest().permitAll()
                .and();

        http.formLogin()
                .loginPage("/login")
                .failureUrl("/403")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .logout()
                .permitAll()
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful")
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);

        /*
		http.authorizeRequests().and().formLogin()//
				// Submit URL of login page.
				.loginProcessingUrl("/j_spring_security_check") // Submit URL
				.loginPage("/login")//
				.defaultSuccessUrl("/userAccountInfo")//
				.failureUrl("/403")//
				.usernameParameter("username")//
				.passwordParameter("password")
				// Config for Logout Page
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
        */

    }

}


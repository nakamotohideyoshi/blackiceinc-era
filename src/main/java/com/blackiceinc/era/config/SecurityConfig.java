package com.blackiceinc.era.config;

import com.blackiceinc.era.services.security.CsrfTokenGeneratorFilter;
import com.blackiceinc.era.services.security.GCDLoginSuccessHandler;
import com.blackiceinc.era.services.security.GCDLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin").password("watermelon700").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        GCDLoginSuccessHandler successHandler = new GCDLoginSuccessHandler();
        GCDLogoutSuccessHandler logoutHandler = new GCDLogoutSuccessHandler();

        http
                .headers()
                //.frameOptions().disable()
                //.addHeaderWriter(new XFrameOptionsHeaderWriter(new WhiteListedAllowFromStrategy(Arrays.asList("www.dzone.com"))))
                .addHeaderWriter(new StaticHeadersWriter("X-Frame-Options","SAMEORIGIN"))
                .and()
                .addFilterAfter(new CsrfTokenGeneratorFilter(), CsrfFilter.class)
                .authorizeRequests()

                .antMatchers("/signup","/about", "/upload_test","/api/**", "/calculator/***", "resources/includes/pdf.html").permitAll() // #4
                .antMatchers("/main/**", "/demo1*").hasRole("USER") // #6

                .and()
                .formLogin().loginPage("/login").failureUrl("/login?error")
                .successHandler(successHandler)
                .usernameParameter("username").passwordParameter("password")
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessHandler(logoutHandler)
                .logoutSuccessUrl("/login?logout")
                .and()
                .csrf();
    }

}

package com.blackiceinc.era.config;

import com.blackiceinc.era.persistence.erau.model.Role;
import com.blackiceinc.era.services.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private EraLoginSuccessHandler successHandler;

    @Autowired
    private EraLogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private EraAuthenticationProvider eraAuthenticationProvider;

    @Autowired
    private Http401UnauthorizedEntryPoint authenticationEntryPoint;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        auth
                .authenticationProvider(eraAuthenticationProvider);
//                .inMemoryAuthentication()
//                .withUser("admin").password("watermelon700").roles("ADMIN");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
             .csrf()
        .and()
            .exceptionHandling()
            .accessDeniedHandler(new CustomAccessDeniedHandler())
            .accessDeniedPage("/login")
            .authenticationEntryPoint(authenticationEntryPoint)
        .and()
            .formLogin()
            .authenticationDetailsSource(new EraAuthDetailsSource())
            .loginPage("/login").failureUrl("/login?error")
            .successHandler(successHandler)
            .usernameParameter("username")
            .passwordParameter("password")
            .permitAll()
        .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessHandler(logoutSuccessHandler)
            .logoutSuccessUrl("/login?logout")
            .deleteCookies("JSESSIONID", "CSRF-TOKEN")
            .invalidateHttpSession(true)
            .permitAll()
        .and()
            .headers()
            .frameOptions()
            .disable()
        .and()
            .addFilterAfter(new CsrfCookieGeneratorFilter(), CsrfFilter.class)
            .authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers("/main").permitAll()
            .antMatchers("/login").permitAll()
            .antMatchers("/logout").permitAll()
            .antMatchers("/ldapClient").permitAll()
            .antMatchers("/api/credit-risk/**").authenticated()
            .antMatchers("/api/bookmark/**").authenticated()
            .antMatchers("/api/user/**").hasAuthority(Role.ROLE_ADMIN)
            .antMatchers("/api/runCalculator/**").hasAuthority(Role.ROLE_ADMIN)
            .antMatchers("/api/configuration/**").hasAnyAuthority(Role.ROLE_ADMIN, Role.ROLE_CONFIGURATION)
            .antMatchers("/api/data-extraction/**").authenticated()
            .antMatchers("/api/stress-testing/**").authenticated()
            .anyRequest().authenticated()
        ;

    }


}

package com.timeclock.web.ClockBeta.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    public void configure(WebSecurity web) throws Exception {
        //Web resources
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/js/**");
        web.ignoring().antMatchers("/scripts/**");
        web.ignoring().antMatchers("/images/**");
        web.ignoring().antMatchers("/assets/**");
        web.ignoring().antMatchers("/error/**");
        web.ignoring().antMatchers("/fonts/**");
        web.ignoring().antMatchers("/img/**");
        web.ignoring().antMatchers("/rest/**"); // test to see if ignores all rest methods
        web.ignoring().antMatchers("/rest/employees");
        web.ignoring().antMatchers("/register");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                // We filter the api/login requests
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                // And filter other requests to check the presence of JWT in header
                .addFilterBefore(new JWTAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);
//            .authorizeRequests()
//            .antMatchers("/", "/hello").permitAll()
//            .antMatchers(HttpMethod.POST, "/rest/lock").permitAll()
//            .anyRequest().authenticated()
//            .and()
//            .formLogin()
//            .loginPage("/login")
//            .permitAll()
//            .defaultSuccessUrl("/hello/business")
//            .and()
//            .addFilterBefore(new JWTLoginFilter("/rest/lock", authenticationManager()),
//                    UsernamePasswordAuthenticationFilter.class)
//            // And filter other requests to check the presence of JWT in header
//            .addFilterBefore(new JWTAuthenticationFilter(),
//                    UsernamePasswordAuthenticationFilter.class)
//            .logout()
//            .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser("jake").password("password").roles("USER");

        auth.jdbcAuthentication().dataSource(dataSource)
            .usersByUsernameQuery("select user_name, password, enabled from user where user_name=?")
            .authoritiesByUsernameQuery("select user_name, role from user_role where user_name=?");
    }
}

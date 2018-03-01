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
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
@EnableWebSecurity
@CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"}, maxAge = 3600)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    CorsFilter corsFilter;

    @Autowired
    CorsRegistry corsRegistry;

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/rest/**")
                .allowedOrigins("https://spring-clock-ui.herokuapp.com", "http://localhost:3000");
    }

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
        web.ignoring().antMatchers("/register");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        addCorsMappings(corsRegistry);
        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST, "/rest/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JWTLoginFilter("/rest/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);
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

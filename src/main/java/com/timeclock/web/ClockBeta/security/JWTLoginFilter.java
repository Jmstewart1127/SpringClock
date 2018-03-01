package com.timeclock.web.ClockBeta.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.timeclock.web.ClockBeta.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"}, maxAge = 3600)
    public Authentication attemptAuthentication(
            HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException, ServletException {
        User creds = new ObjectMapper()
            .readValue(req.getInputStream(), User.class);
        return getAuthenticationManager().authenticate(
            new UsernamePasswordAuthenticationToken(
                creds.getUserName(),
                creds.getPassword(),
                Collections.emptyList()
            )
        );
    }

    @Override
    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"}, maxAge = 3600)
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res, FilterChain chain,
            Authentication auth) throws IOException, ServletException {
        TokenAuthenticationService
            .addAuthentication(res, auth.getName());
    }
}

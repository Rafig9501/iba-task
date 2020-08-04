package com.ibabanking.zuul.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibabanking.zuul.auth.UserForAuthentication;
import com.ibabanking.zuul.config.JwtConfig;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

@Slf4j
public class JwtFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;
    private final AuthenticationManager authenticationManager;

    public JwtFilter(JwtConfig jwtConfig,
                     SecretKey secretKey,
                     AuthenticationManager authenticationManager) {
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            UserForAuthentication authenticationRequest
                    = new ObjectMapper().readValue(request.getInputStream(), UserForAuthentication.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
            );
            return authenticationManager.authenticate(authentication);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusMonths(1)))
                .compact();

        response.addHeader(jwtConfig.getTokenHeader(), jwtConfig.getTokenPrefix() + " " + token);
    }
}

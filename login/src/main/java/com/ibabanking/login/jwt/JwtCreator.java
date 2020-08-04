package com.ibabanking.login.jwt;

import com.ibabanking.login.dto.UserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtCreator {

    @Value("${jwt.secret.key}")
    String SECRET_KEY;

    public String generateToken(UserDto userDto) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDto.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {

        String token = Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
        return "Bearer " + token;
    }
}

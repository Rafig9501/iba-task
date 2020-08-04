package com.ibabanking.zuul.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@PropertySource("classpath:application.properties")
@Component
public class JwtConfig {

    @Value("jwt.token.secret")
    String secretKey;

    @Value("jwt.token.prefix")
    String tokenPrefix;

    private final Long tokenExpirationDay=3600000L;

    @Value("jwt.token.header")
    String tokenHeader;
}

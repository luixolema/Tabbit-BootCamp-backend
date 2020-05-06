package com.tabit.dcm2.service.impl;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtsFactory {
    JwtBuilder createJwtBuilder() {
        return Jwts.builder();
    }

    JwtParser createParser() {
        return Jwts.parser();
    }

}

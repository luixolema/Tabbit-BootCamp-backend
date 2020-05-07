package com.tabit.dcm2.service.impl;

import com.tabit.dcm2.commons.ApplicationProperties;
import com.tabit.dcm2.service.IJwtTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JwtTokenService implements IJwtTokenService {

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private JwtsFactory jwtsFactory;

    @Override
    public String generateToken(String login) {
        final Date createDate = new Date();
        final Date expirationDate = calculateExpirationDate(createDate);

        return jwtsFactory.createJwtBuilder()
                .setClaims(new HashMap<>())
                .setSubject(login)
                .setIssuedAt(createDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, applicationProperties.getSecret())
                .compact();
    }

    @Override
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return jwtsFactory.createParser()
                .setSigningKey(applicationProperties.getSecret())
                .parseClaimsJws(token)
                .getBody();
    }

    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + applicationProperties.getExpiration());
    }
}

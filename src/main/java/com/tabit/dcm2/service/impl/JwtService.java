package com.tabit.dcm2.service.impl;

import com.tabit.dcm2.config.jwt.JwtConfig;
import com.tabit.dcm2.config.jwt.JwtSecretKey;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private JwtConfig jwtConfig;
    private JwtSecretKey jwtSecretKey;

    public JwtService(JwtConfig jwtConfig, JwtSecretKey jwtSecretKey) {
        this.jwtConfig = jwtConfig;
        this.jwtSecretKey = jwtSecretKey;
    }

    public String generateToken(String username, Map<String, Object> claims) {
        final Date createdDate = new Date();
        final LocalDate expirationDate = LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays());

        return Jwts.builder()
                .addClaims(claims)
                .setSubject(username)
                .setIssuedAt(createdDate)
                .setExpiration(java.sql.Date.valueOf(expirationDate))
                .signWith(jwtSecretKey.secretKey(), SignatureAlgorithm.HS512)
                .compact();
    }


    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }


    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }


    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }


    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(jwtSecretKey.secretKey()).build().parseClaimsJws(token).getBody();
    }


    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

}
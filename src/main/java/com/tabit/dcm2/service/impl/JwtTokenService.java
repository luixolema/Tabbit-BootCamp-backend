package com.tabit.dcm2.service.impl;

import com.tabit.dcm2.service.IJwtTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;

@Service
public class JwtTokenService implements IJwtTokenService {

    private static final String SECRET = "mySecret";
    private static final Long EXPIRATION = 30000L;

    @Override
    public String generateToken(String login) {
        final Date createDate = new Date();
        final Date expirationDate = calculateExpirationDate(createDate);

        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(login)
                .setIssuedAt(createDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenNotExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.after(new Date());
    }

    public Optional<Boolean> validateToken(String token) {
        return isTokenNotExpired(token) ? Optional.of(Boolean.TRUE) : Optional.empty();
    }

    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + EXPIRATION);
    }
}

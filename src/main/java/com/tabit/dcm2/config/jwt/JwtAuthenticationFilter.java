package com.tabit.dcm2.config.jwt;

import com.google.common.base.Strings;
import com.tabit.dcm2.config.security.UserDetailsService;
import com.tabit.dcm2.service.impl.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    public JwtAuthenticationFilter(SecretKey secretKey,
                                   JwtConfig jwtConfig,
                                   UserDetailsService userDetailsService,
                                   JwtService jwtService
    ) {
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        Optional<String> optionalToken = getRequestToken(request);

        if (optionalToken.isPresent()) {
            String token = optionalToken.get();
            setAuthentication(token);
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }

        filterChain.doFilter(request, response);


    }

    private void setAuthentication(String token) {
        Jws<Claims> claimsJws = null;

        try {
            claimsJws = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);

        } catch (JwtException ex) {
            throw new BadCredentialsException("The jwt token is not valid");
        }

        Claims body = claimsJws.getBody();

        String username = body.getSubject();

//            Object authorities = (List<Map<String, String>>) body.get("authorities");

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                username,
                null,
                Collections.emptyList()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    private Optional<String> getRequestToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(jwtConfig.getAuthorizationHeader());
        Optional<String> token = Optional.empty();

        if (!Strings.isNullOrEmpty(authorizationHeader) && authorizationHeader.startsWith(jwtConfig.getTokenPrefix())) {
            token = Optional.of(authorizationHeader.replace(jwtConfig.getTokenPrefix(), ""));
        }

        return token;
    }
}

package com.tabit.dcm2.security;

import com.tabit.dcm2.exception.JwtAuthenticationException;
import com.tabit.dcm2.service.impl.JwtTokenService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationProvider.class);

    @Autowired
    private JwtTokenService jwtTokenService;

    @Override
    public Authentication authenticate(Authentication authentication) {
        String token = (String) authentication.getCredentials();
        try {
            String username = jwtTokenService.getUsernameFromToken(token);

            return new JwtAuthenticatedProfile(username);
        } catch (ExpiredJwtException e) {
            LOGGER.info("Token expired");
            throw new JwtAuthenticationException("Invalid token", e);
        } catch (JwtException e) {
            LOGGER.warn("Invalid Token");
            throw new JwtAuthenticationException("Invalid token", e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthentication.class.equals(authentication);
    }
}

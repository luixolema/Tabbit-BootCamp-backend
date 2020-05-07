package com.tabit.dcm2.security;

import com.tabit.dcm2.exception.JwtAuthenticationException;
import com.tabit.dcm2.service.impl.JwtTokenService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private JwtTokenService jwtTokenService;

    @Override
    public Authentication authenticate(Authentication authentication) {
        String token = (String) authentication.getCredentials();
        try {
            String username = jwtTokenService.getUsernameFromToken(token);

            return new JwtAuthenticatedProfile(username);
        } catch (ExpiredJwtException e) {
            throw new JwtAuthenticationException("Token expired", e);
        } catch (JwtException e) {
            throw new JwtAuthenticationException("Invalid token error", e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthentication.class.equals(authentication);
    }
}

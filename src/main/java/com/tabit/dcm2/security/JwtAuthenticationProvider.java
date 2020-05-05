package com.tabit.dcm2.security;

import com.tabit.dcm2.exception.JwtAuthenticationException;
import com.tabit.dcm2.service.impl.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private JwtTokenService jwtService;

    @Override
    public Authentication authenticate(Authentication authentication) {
        String token = (String) authentication.getCredentials();
        String username = jwtService.getUsernameFromToken(token);

        return jwtService.validateToken(token)
                .map(aBoolean -> new JwtAuthenticatedProfile(username))
                .orElseThrow(() -> new JwtAuthenticationException("JWT Token validation failed"));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthentication.class.equals(authentication);
    }
}

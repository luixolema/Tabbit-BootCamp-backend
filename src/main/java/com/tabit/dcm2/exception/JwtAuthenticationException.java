package com.tabit.dcm2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class JwtAuthenticationException extends AuthenticationException {
    private static final long serialVersionUID = 1L;

    public JwtAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}

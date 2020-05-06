package com.tabit.dcm2.controller;

import com.tabit.dcm2.config.jwt.AuthenticationRequest;
import com.tabit.dcm2.config.jwt.AuthenticationResponse;
import com.tabit.dcm2.config.security.UserDetailsService;
import com.tabit.dcm2.service.impl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class AuthenticationController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public AuthenticationResponse createCustomer(@RequestBody AuthenticationRequest request) {

        authenticate(request.getUsername(), request.getPassword());
        final String token = jwtService.generateToken(request.getUsername(), Collections.emptyMap());

        return new AuthenticationResponse(token);
    }


    private void authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AccountExpiredException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", e);
        }
    }
}

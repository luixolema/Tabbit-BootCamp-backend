package com.tabit.dcm2.service.impl;

import com.tabit.dcm2.exception.ResourceNotFoundException;
import com.tabit.dcm2.repository.IUserRepo;
import com.tabit.dcm2.security.JwtTokenResponse;
import com.tabit.dcm2.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService {
    @Autowired
    private IUserRepo userRepo;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public JwtTokenResponse generateJwtToken(String login, String password) {
        return userRepo.findByLogin(login)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .map(user -> new JwtTokenResponse(jwtTokenService.generateToken(login)))
                .orElseThrow(() -> new ResourceNotFoundException("Wrong user or password"));
    }
}

package com.tabit.dcm2.service.impl;

import com.tabit.dcm2.exception.ResourceNotFoundException;
import com.tabit.dcm2.repository.IUserRepo;
import com.tabit.dcm2.security.JwtTokenResponse;
import com.tabit.dcm2.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService {
    @Autowired
    private IUserRepo userRepo;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Override
    public JwtTokenResponse generateJwtToken(String login, String password) {
        return userRepo.findByLogin(login)
                .filter(user -> user.getPassword().matches(password))
                .map(user -> new JwtTokenResponse(jwtTokenService.generateToken(login)))
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}

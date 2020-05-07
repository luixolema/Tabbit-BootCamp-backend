package com.tabit.dcm2.service;

public interface IJwtTokenService {
    String generateToken(String login);

    String getUsernameFromToken(String token);
}

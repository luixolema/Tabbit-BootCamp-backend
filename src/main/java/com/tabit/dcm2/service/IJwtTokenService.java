package com.tabit.dcm2.service;

import java.util.Optional;

public interface IJwtTokenService {
    String generateToken(String login);

    String getUsernameFromToken(String token);

    Optional<Boolean> validateToken(String token);
}

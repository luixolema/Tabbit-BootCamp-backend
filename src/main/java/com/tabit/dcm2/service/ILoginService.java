package com.tabit.dcm2.service;

import com.tabit.dcm2.security.JwtTokenResponse;

public interface ILoginService {
    JwtTokenResponse generateJwtToken(String login, String password);
}

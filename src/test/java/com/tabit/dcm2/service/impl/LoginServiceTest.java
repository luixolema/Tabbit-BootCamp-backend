package com.tabit.dcm2.service.impl;

import com.tabit.dcm2.entity.RandomUser;
import com.tabit.dcm2.entity.User;
import com.tabit.dcm2.exception.ResourceNotFoundException;
import com.tabit.dcm2.repository.IUserRepo;
import com.tabit.dcm2.security.JwtTokenResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {
    @Mock
    private IUserRepo userRepo;

    @Mock
    private JwtTokenService jwtTokenService;

    @InjectMocks
    private LoginService loginService;

    @Test
    public void generateJwtToken_shall_return_valid_JwtTokenResponse() {
        // given
        String token = "testToken";
        User user = RandomUser.createRandomUser();
        when(userRepo.findByLogin(user.getLogin())).thenReturn(Optional.of(user));
        when(jwtTokenService.generateToken(user.getLogin())).thenReturn(token);

        // when
        JwtTokenResponse jwtTokenResponse = loginService.generateJwtToken(user.getLogin(), user.getPassword());

        // then
        assertThat(jwtTokenResponse.getToken()).isEqualTo(token);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void generateJwtToken_shall_throw_exception() {
        // given
        User user = RandomUser.createRandomUser();
        when(userRepo.findByLogin(user.getLogin())).thenReturn(Optional.empty());

        // when
        loginService.generateJwtToken(user.getLogin(), user.getPassword());
    }
}

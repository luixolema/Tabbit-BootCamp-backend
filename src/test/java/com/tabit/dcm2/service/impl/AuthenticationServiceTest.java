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
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationServiceTest {
    @Mock
    private IUserRepo userRepo;

    @Mock
    private JwtTokenService jwtTokenService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthenticationService authenticationService;

    private String password = "password";

    @Test
    public void generateJwtToken_shall_return_valid_JwtTokenResponse() {
        // given
        String token = "testToken";
        User user = RandomUser.createRandomUser();
        when(userRepo.findByLogin(user.getLogin())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(password, user.getPassword())).thenReturn(true);
        when(jwtTokenService.generateToken(user.getLogin())).thenReturn(token);

        // when
        JwtTokenResponse jwtTokenResponse = authenticationService.generateJwtToken(user.getLogin(), password);

        // then
        verify(passwordEncoder).matches(password, user.getPassword());
        assertThat(jwtTokenResponse.getToken()).isEqualTo(token);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void generateJwtToken_shall_throw_exception_when_no_user_found() {
        // given
        User user = RandomUser.createRandomUser();
        when(userRepo.findByLogin(user.getLogin())).thenReturn(Optional.empty());

        // when
        authenticationService.generateJwtToken(user.getLogin(), user.getPassword());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void generateJwtToken_shall_throw_exception_password_doesnt_match() {
        // given
        User user = RandomUser.createRandomUser();
        when(userRepo.findByLogin(user.getLogin())).thenReturn(Optional.of(user));

        // when
        authenticationService.generateJwtToken(user.getLogin(), "another password");
    }
}

package com.tabit.dcm2.controller;

import com.tabit.dcm2.security.JwtTokenResponse;
import com.tabit.dcm2.service.ILoginService;
import com.tabit.dcm2.service.dto.LoginDto;
import com.tabit.dcm2.service.dto.RandomLoginDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {
    @Mock
    private ILoginService loginService;

    @InjectMocks
    private LoginController loginController;

    @Test
    public void loginUser_shall_return_JwtTokenResponse_of_the_loggedIn_user() {
        // given
        String token = "testToken";
        LoginDto loginDto = RandomLoginDto.createRandomLoginDto();
        when(loginService.generateJwtToken(loginDto.getLogin(), loginDto.getPassword())).thenReturn(new JwtTokenResponse(token));

        // when
        JwtTokenResponse jwtTokenResponse = loginController.loginUser(loginDto);

        // then
        assertThat(jwtTokenResponse.getToken()).isEqualTo(token);
    }
}

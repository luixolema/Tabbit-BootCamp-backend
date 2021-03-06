package com.tabit.dcm2.controller;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.DiveCenter;
import com.tabit.dcm2.entity.RandomDiveCenter;
import com.tabit.dcm2.entity.RandomUser;
import com.tabit.dcm2.entity.User;
import com.tabit.dcm2.security.JwtTokenResponse;
import com.tabit.dcm2.service.ILoginService;
import com.tabit.dcm2.service.dto.LoginDto;
import com.tabit.dcm2.service.dto.RandomLoginDto;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginControllerRestIntegrationTest extends AbstractRestIntegrationTest {
    @Autowired
    private ILoginService loginService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String password = "password";
    private User user;
    private DiveCenter diveCenter;

    @Before
    public void setUp() {
        diveCenter = RandomDiveCenter.createRandomDiveCenterWithoutId();
        diveCenterRule.persist(ImmutableList.of(diveCenter));
    }

    @Test
    public void loginUser_shall_return_JwtTokenResponse_of_the_loggedIn_user() {
        // given
        LoginDto loginDto = RandomLoginDto.createRandomLoginDto();
        User newUser = RandomUser.createRandomUserFromLoginDto(loginDto);
        newUser.setDiveCenter(diveCenter);
        userRule.persist(ImmutableList.of(newUser));
        HttpEntity<LoginDto> entity = createHttpEntity(loginDto);

        // when
        ResponseEntity<JwtTokenResponse> response = restTemplate.exchange(
                "/login",
                HttpMethod.POST,
                entity,
                JwtTokenResponse.class);

        // then
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    public void loginUser_shall_throw_exception_when_not_found() {
        // given
        LoginDto loginDto = RandomLoginDto.createRandomLoginDto();
        HttpEntity<LoginDto> entity = createHttpEntity(loginDto);

        // when
        ResponseEntity<JwtTokenResponse> response = restTemplate.exchange(
                "/login",
                HttpMethod.POST,
                entity,
                JwtTokenResponse.class);

        // then
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.NOT_FOUND);
        assertThat(response.getBody().getToken()).isNull();
    }
}

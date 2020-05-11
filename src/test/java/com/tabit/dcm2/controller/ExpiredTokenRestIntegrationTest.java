package com.tabit.dcm2.controller;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.DiveCenter;
import com.tabit.dcm2.entity.RandomDiveCenter;
import com.tabit.dcm2.entity.RandomUser;
import com.tabit.dcm2.entity.User;
import com.tabit.dcm2.service.ILoginService;
import com.tabit.dcm2.service.dto.GuestOverviewDto;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@TestPropertySource(properties = {"jwt.secret = secret", "jwt.expiration = 0"})
public class ExpiredTokenRestIntegrationTest extends AbstractRestIntegrationTest {
    @Autowired
    private ILoginService loginService;

    private User user;
    private String authToken;
    private String password = "password";

    @Before
    public void setUp() {
        DiveCenter diveCenter = RandomDiveCenter.createRandomDiveCenterWithoutId();
        diveCenterRule.persist(ImmutableList.of(diveCenter));

        user = RandomUser.createRandomUserWithPasswordWithoutId(password);
        user.setDiveCenter(diveCenter);
        userRule.persist(ImmutableList.of(user));

        authToken = loginService.generateJwtToken(user.getLogin(), password).getToken();
    }

    @Test
    public void expiredToken_shall_throw_Exception() {
        // given
        HttpEntity<?> entity = createHttpEntity(authToken);

        // when
        ResponseEntity<GuestOverviewDto> response = restTemplate.exchange(
                "/api/guests/",
                HttpMethod.GET,
                entity,
                GuestOverviewDto.class
        );

        //then
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.UNAUTHORIZED);
    }
}

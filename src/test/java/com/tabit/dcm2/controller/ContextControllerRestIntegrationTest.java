package com.tabit.dcm2.controller;


import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.DiveCenter;
import com.tabit.dcm2.entity.RandomDiveCenter;
import com.tabit.dcm2.entity.RandomUser;
import com.tabit.dcm2.entity.User;
import com.tabit.dcm2.service.ILoginService;
import com.tabit.dcm2.service.dto.ContextInfoDto;
import com.tabit.dcm2.testutils.ContextInfoDtoAssertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class ContextControllerRestIntegrationTest extends AbstractRestIntegrationTest {

    @Autowired
    private ILoginService loginService;

    private String authToken;
    private User user;

    @Before
    public void setUp() {
        //given
        DiveCenter diveCenter = RandomDiveCenter.createRandomDiveCenterWithoutId();
        diveCenterRule.persist(ImmutableList.of(diveCenter));

        user = RandomUser.createRandomUserWithPasswordWithoutId("password");
        user.setDiveCenter(diveCenter);
        userRule.persist(ImmutableList.of(user));

        authToken = loginService.generateJwtToken(user.getLogin(), "password").getToken();
    }

    @Test
    public void getContextInfo_shall_return_ContextInfoDto() {
        // given
        HttpEntity<?> entity = createHttpEntity(authToken);

        // when
        ResponseEntity<ContextInfoDto> response = restTemplate.exchange(
                "/contextInfo",
                HttpMethod.GET,
                entity,
                ContextInfoDto.class
        );

        // then
        ContextInfoDto contextInfoDto = response.getBody();
        assertThat(contextInfoDto).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        ContextInfoDtoAssertions.assertDto(contextInfoDto, user.getName(), user.getDiveCenter().getName());
    }

}
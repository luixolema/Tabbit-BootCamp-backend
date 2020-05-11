package com.tabit.dcm2.controller;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.*;
import com.tabit.dcm2.service.ILoginService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class BoxManagementControllerRestIntegrationTest extends AbstractRestIntegrationTest {
    @Autowired
    private BoxManagementController boxManagementController;

    @Autowired
    private ILoginService loginService;

    private BoxManagement reservedBoxManager;
    private User user;
    private String password = "password";
    private String authToken;

    @Before
    public void setUp() {
        //given
        DiveCenter diveCenter = RandomDiveCenter.createRandomDiveCenterWithoutId();
        diveCenterRule.persist(ImmutableList.of(diveCenter));

        reservedBoxManager = RandomBoxManagement.createRandomBoxManagementWithoutIdGivenDiveCenter(diveCenter);
        boxManagementRule.persist(ImmutableList.of(reservedBoxManager));

        user = RandomUser.createRandomUserWithPasswordWithoutId(password);
        user.setDiveCenter(diveCenter);
        userRule.persist(ImmutableList.of(user));

        authToken = loginService.generateJwtToken(user.getLogin(), password).getToken();
    }

    @Test
    public void isBoxFree_shall_return_the_right_value() {
        // given
        HttpEntity<?> entity = createHttpEntity(authToken);

        // when
        ResponseEntity<Boolean> responseIsBoxFree = restTemplate.exchange(
                "/api/box/" + reservedBoxManager.getBoxNumber() + "/isFree",
                HttpMethod.GET,
                entity,
                Boolean.class
        );

        // then
        assertThat(responseIsBoxFree.getBody()).isFalse();

        // when
        responseIsBoxFree = restTemplate.exchange(
                "/api/box/no_reserved/isFree",
                HttpMethod.GET,
                entity,
                Boolean.class
        );

        // then
        assertThat(responseIsBoxFree.getBody()).isTrue();
    }

}
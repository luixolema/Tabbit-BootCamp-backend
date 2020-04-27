package com.tabit.dcm2.controller;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.BoxManagement;
import com.tabit.dcm2.entity.RandomBoxManagement;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class BoxManagementControllerRestIntegrationTest extends AbstractRestIntegrationTest {
    @Autowired
    private BoxManagementController boxManagementController;

    private BoxManagement reservedBoxManager;

    @Before
    public void setUp() {

        //given
        reservedBoxManager = RandomBoxManagement.createRandomBoxManagement();
        ;

        boxManagementRule.persist(ImmutableList.of(reservedBoxManager));
    }

    @Test
    public void isBoxFree_shall_return_the_right_value() {
        // given
        String freeBoxNumber = "no_reserved";

        // when
        ResponseEntity<Boolean> responseIsBoxFree = restTemplate.exchange(
                "/api/box/" + reservedBoxManager.getBoxNumber() + "/isFree",
                HttpMethod.GET,
                null,
                Boolean.class
        );

        // then
        assertThat(responseIsBoxFree.getBody()).isFalse();

        // when
        responseIsBoxFree = restTemplate.exchange(
                "/api/box/" + freeBoxNumber + "/isFree",
                HttpMethod.GET,
                null,
                Boolean.class
        );

        // then
        assertThat(responseIsBoxFree.getBody()).isTrue();
    }

}
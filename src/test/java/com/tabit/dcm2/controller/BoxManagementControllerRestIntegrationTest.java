package com.tabit.dcm2.controller;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.BoxManagement;
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

    private BoxManagement boxManagement;

    @Before
    public void setUp() {

        //given
        boxManagement = new BoxManagement();
        boxManagement.setBoxNumber("test");

        boxManagementRule.persist(ImmutableList.of(boxManagement));
    }


    @Test
    public void isBoxFree_shall_return_the_right_value() {
        // given
        HttpEntity<String> activeBox = createHttpEntity(boxManagement.getBoxNumber());
        HttpEntity<String> freeBox = createHttpEntity(boxManagement.getBoxNumber() + "Update");

        // when
        ResponseEntity<Boolean> responseIsBoxFree = restTemplate.exchange("/api/box/isFree", HttpMethod.POST, activeBox, Boolean.class);

        // then
        assertThat(responseIsBoxFree.getBody()).isFalse();

        // when
        responseIsBoxFree = restTemplate.exchange("/api/box/isFree", HttpMethod.POST, freeBox, Boolean.class);

        // then
        assertThat(responseIsBoxFree.getBody()).isTrue();
    }

}
package com.tabit.dcm2.controller;

import com.tabit.dcm2.entity.DiveCenter;
import com.tabit.dcm2.entity.RandomDiveCenter;
import com.tabit.dcm2.entity.RandomUser;
import com.tabit.dcm2.entity.User;
import com.tabit.dcm2.service.dto.ContextInfoDto;
import com.tabit.dcm2.service.impl.AuthenticationService;
import com.tabit.dcm2.testutils.ContextInfoDtoAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ContextControllerTest {

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private ContextController contextController;

    @Test
    public void getContextInfo_shall_return_ContextInfoDto() {
        // given
        DiveCenter diveCenter = RandomDiveCenter.createRandomDiveCenter();
        User user = RandomUser.createRandomUserGivenDiveCenter(diveCenter);

        when(authenticationService.getLoggedInUser()).thenReturn(user);

        // when
        ContextInfoDto contextInfoDto = contextController.getContextInfo();

        // then
        ContextInfoDtoAssertions.assertDto(contextInfoDto, user.getName(), diveCenter.getName());
    }
}
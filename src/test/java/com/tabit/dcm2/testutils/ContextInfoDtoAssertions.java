package com.tabit.dcm2.testutils;

import com.tabit.dcm2.service.dto.ContextInfoDto;

import static org.assertj.core.api.Assertions.assertThat;

public class ContextInfoDtoAssertions {

    public static void assertDto(ContextInfoDto actualDto, String expectedUserName, String expectedDiveCenterName) {
        assertThat(actualDto.getUserName()).isEqualTo(expectedUserName);
        assertThat(actualDto.getDiveCenterName()).isEqualTo(expectedDiveCenterName);
    }
}

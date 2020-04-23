package com.tabit.dcm2.commons;

import com.tabit.dcm2.exception.BeanValidationException;
import com.tabit.dcm2.exception.ProgramException;
import com.tabit.dcm2.service.dto.RandomStayDetailsForCheckInDto;
import com.tabit.dcm2.service.dto.StayDetailsForCheckInDto;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AbstractNonNullValidatingBeanBuilderTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void null_value_throws_exception() {
        expectedException.expect(BeanValidationException.class);
        expectedException.expectMessage("StayDetailsForCheckInDto.divesAmount: Null value is not allowed");
        RandomStayDetailsForCheckInDto.createRandomStayDetailsForCheckInDtoBuilder().withDivesAmount(null).build();
    }

    @Test
    public void building_a_bean_twice_throws_exception() {
        StayDetailsForCheckInDto.Builder randomStayDetailsForCheckInDtoBuilder = RandomStayDetailsForCheckInDto.createRandomStayDetailsForCheckInDtoBuilder();
        randomStayDetailsForCheckInDtoBuilder.build();

        expectedException.expect(ProgramException.class);
        expectedException.expectMessage("Bean was already build");
        randomStayDetailsForCheckInDtoBuilder.build();
    }
}
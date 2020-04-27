package com.tabit.dcm2.service.dto;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.exception.BeanValidationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import java.time.LocalDate;

@RunWith(Theories.class)
public class StayDetailsForCheckInDtoTest {
    private static final LocalDate NOW = LocalDate.now();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @DataPoints
    public static BeanValidationExceptionTestCase<StayDetailsForCheckInDto.Builder>[] testCases = ImmutableList.of(
            leaveDateInThePast()).toArray(new BeanValidationExceptionTestCase[0]);

    @Theory
    @Test
    public void invalid_dto_throws_exception(BeanValidationExceptionTestCase<StayDetailsForCheckInDto.Builder> testCase) {
        expectedException.expect(BeanValidationException.class);
        expectedException.expectMessage(testCase.getExpectedMessage());
        testCase.getInvalidDto().build();
    }

    private static BeanValidationExceptionTestCase<StayDetailsForCheckInDto.Builder> leaveDateInThePast() {
        return new BeanValidationExceptionTestCase<StayDetailsForCheckInDto.Builder>()
                .withInvalidDto(RandomStayDetailsForCheckInDto.createRandomStayDetailsForCheckInDtoBuilder()
                        .withCheckInDate(NOW.minusDays(2))
                        .withLeaveDate(NOW.minusDays(1))
                        .withArriveDate(NOW.minusDays(3)))
                .withExpectedMessage("StayDetailsForCheckInDto.leaveDate: must not be in the past");
    }
}
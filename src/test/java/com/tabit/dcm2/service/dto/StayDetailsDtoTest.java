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
public class StayDetailsDtoTest {
    protected static final LocalDate NOW = LocalDate.now();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @DataPoints
    public static BeanValidationExceptionTestCase<StayDetailsDto.Builder>[] testCases = ImmutableList.of(
            leaveDateNotInThePastWhenActive(),
            checkOutDateBeforeCheckInDate(),
            checkOutDateBetweenArriveDateAndLeaveDate()).toArray(new BeanValidationExceptionTestCase[0]);

    @Theory
    @Test
    public void invalid_dto_throws_exception(BeanValidationExceptionTestCase<StayDetailsDto.Builder> testCase) {
        expectedException.expect(BeanValidationException.class);
        expectedException.expectMessage(testCase.getExpectedMessage());
        testCase.getInvalidDto().build();
    }

    private static BeanValidationExceptionTestCase<StayDetailsDto.Builder> checkOutDateBeforeCheckInDate() {
        return new BeanValidationExceptionTestCase<StayDetailsDto.Builder>()
                .withInvalidDto(RandomStayDetailsDto.createRandomStayDetailsDtoBuilder().withCheckOutDate(NOW.minusDays(1)).withCheckInDate(NOW))
                .withExpectedMessage("StayDetailsDto.checkOutDate: must be after checkInDate");
    }

    private static BeanValidationExceptionTestCase<StayDetailsDto.Builder> checkOutDateBetweenArriveDateAndLeaveDate() {
        return new BeanValidationExceptionTestCase<StayDetailsDto.Builder>()
                .withInvalidDto(RandomStayDetailsDto.createRandomStayDetailsDtoBuilder().withCheckInDate(NOW).withCheckOutDate(NOW.plusDays(2)).withArriveDate(NOW).withLeaveDate(NOW.plusDays(1)))
                .withExpectedMessage("StayDetailsDto.checkOutDate: must be between arriveDate and leaveDate");
    }

    private static BeanValidationExceptionTestCase<StayDetailsDto.Builder> leaveDateNotInThePastWhenActive() {
        return new BeanValidationExceptionTestCase<StayDetailsDto.Builder>()
                .withInvalidDto(RandomStayDetailsDto.createRandomStayDetailsDtoBuilder()
                        .withActive(true)
                        .withCheckInDate(NOW.minusDays(2)).withCheckOutDate(null).withArriveDate(NOW.minusDays(2)).withLeaveDate(NOW.minusDays(1)))
                .withExpectedMessage("StayDetailsDto.leaveDate: must not be in the past");
    }

}
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
public class AbstractStayDetailsDtoTest {
    protected static final LocalDate NOW = LocalDate.now();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @DataPoints
    public static BeanValidationExceptionTestCase<StayDetailsForCheckInDto.Builder>[] testCases = ImmutableList.of(
            negativDivesAmount(),
            leaveDateBeforArriveDate(),
            checkInNotBetweenLeaveDateAndArriveDate()).toArray(new BeanValidationExceptionTestCase[0]);

    @Theory
    @Test
    public void invalid_dto_throws_exception(BeanValidationExceptionTestCase<StayDetailsForCheckInDto.Builder> testCase) {
        expectedException.expect(BeanValidationException.class);
        expectedException.expectMessage(testCase.getExpectedMessage());
        testCase.getInvalidDto().build();
    }

    private static BeanValidationExceptionTestCase<StayDetailsForCheckInDto.Builder> negativDivesAmount() {
        return new BeanValidationExceptionTestCase<StayDetailsForCheckInDto.Builder>()
                .withInvalidDto(RandomStayDetailsForCheckInDto.createRandomStayDetailsForCheckInDtoBuilder().withDivesAmount(-1))
                .withExpectedMessage("AbstractStayDetailsDto.divesAmount: Integer must be 0 or greater");
    }

    private static BeanValidationExceptionTestCase<StayDetailsForCheckInDto.Builder> leaveDateBeforArriveDate() {
        return new BeanValidationExceptionTestCase<StayDetailsForCheckInDto.Builder>()
                .withInvalidDto(RandomStayDetailsForCheckInDto.createRandomStayDetailsForCheckInDtoBuilder().withLeaveDate(NOW.minusDays(1)).withArriveDate(NOW))
                .withExpectedMessage("AbstractStayDetailsDto.leaveDate: must be after arriveDate");
    }

    private static BeanValidationExceptionTestCase<StayDetailsForCheckInDto.Builder> checkInNotBetweenLeaveDateAndArriveDate() {
        return new BeanValidationExceptionTestCase<StayDetailsForCheckInDto.Builder>()
                .withInvalidDto(RandomStayDetailsForCheckInDto.createRandomStayDetailsForCheckInDtoBuilder()
                        .withLeaveDate(NOW.plusDays(1))
                        .withArriveDate(NOW)
                        .withCheckInDate(NOW.minusDays(1)))
                .withExpectedMessage("AbstractStayDetailsDto.checkInDate: must be between arriveDate and leaveDate");
    }

}
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
public class StaySummaryDtoTest {
    protected static final LocalDate NOW = LocalDate.now();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @DataPoints
    public static BeanValidationExceptionTestCase<StaySummaryDto.Builder>[] testCases = ImmutableList.of(
            leaveDateBeforeArriveDate(),
            leaveDateInThePastWhenActive()).toArray(new BeanValidationExceptionTestCase[0]);

    @Theory
    @Test
    public void invalid_dto_throws_exception(BeanValidationExceptionTestCase<StaySummaryDto.Builder> testCase) {
        expectedException.expect(BeanValidationException.class);
        expectedException.expectMessage(testCase.getExpectedMessage());
        testCase.getInvalidDto().build();
    }

    private static BeanValidationExceptionTestCase<StaySummaryDto.Builder> leaveDateBeforeArriveDate() {
        return new BeanValidationExceptionTestCase<StaySummaryDto.Builder>()
                .withInvalidDto(RandomStaySummaryDto.createRandomStaySummaryDtoBuilder().withActive(false).withLeaveDate(NOW.minusDays(2)).withArriveDate(NOW.minusDays(1)))
                .withExpectedMessage("StaySummaryDto.leaveDate: must be after arriveDate");
    }

    private static BeanValidationExceptionTestCase<StaySummaryDto.Builder> leaveDateInThePastWhenActive() {
        return new BeanValidationExceptionTestCase<StaySummaryDto.Builder>()
                .withInvalidDto(RandomStaySummaryDto.createRandomStaySummaryDtoBuilder().withActive(true).withLeaveDate(NOW.minusDays(1)).withArriveDate(NOW.minusDays(2)))
                .withExpectedMessage("StaySummaryDto.leaveDate: must not be in the past");
    }
}
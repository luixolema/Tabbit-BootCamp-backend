package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.exception.BeanValidationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;

public class StaySummaryDtoTest {
    protected static final LocalDate NOW = LocalDate.now();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void invalid_dto_with_leave_date_before_arrive_date_throws_exception() {
        expectedException.expect(BeanValidationException.class);
        expectedException.expectMessage("StaySummaryDto.leaveDate: must be after arriveDate");
        RandomStaySummaryDto.createRandomStaySummaryDtoBuilder().withLeaveDate(NOW.minusDays(1)).withArriveDate(NOW).build();
    }
}
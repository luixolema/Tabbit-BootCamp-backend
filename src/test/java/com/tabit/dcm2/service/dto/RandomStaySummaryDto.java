package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.testutils.ValueProvider;

import java.time.LocalDate;

public class RandomStaySummaryDto {
    public static StaySummaryDto createRandomStaySummaryDto() {
        return createRandomStaySummaryDtoBuilder().build();
    }

    public static StaySummaryDto.Builder createRandomStaySummaryDtoBuilder() {
        ValueProvider valueProvider = new ValueProvider();
        Integer randomValue = valueProvider.randomIntBetween(1, 10);
        LocalDate arriveDate = LocalDate.now().minusDays(randomValue);
        LocalDate leaveDate = arriveDate.plusDays(randomValue);

        return new StaySummaryDto.Builder()
                .withId(valueProvider.randomId())
                .withActive(valueProvider.randomBoolean())
                .withArriveDate(arriveDate)
                .withLeaveDate(leaveDate);
    }
}

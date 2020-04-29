package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.testutils.ValueProvider;

import java.time.LocalDate;

public class RandomLoanDetailsDto {
    public static LoanDetailsDto createRandomLoanDetailsDto() {
        return createRandomLoanDetailsDtoBuilder().build();
    }

    public static LoanDetailsDto.Builder createRandomLoanDetailsDtoBuilder() {
        ValueProvider valueProvider = new ValueProvider();
        LocalDate today = LocalDate.now();
        Integer randomValue = valueProvider.randomIntBetween(1, 10);
        LocalDate dateOut = today.minusDays(randomValue);
        LocalDate dateReturn = valueProvider.randomBoolean() ? today.plusDays(randomValue) : null;

        return new LoanDetailsDto.Builder()
                .withId(valueProvider.randomId())
                .withSerialNumber(valueProvider.randomString("serial_"))
                .withType(valueProvider.randomString("type_"))
                .withDateOut(dateOut)
                .withDateReturn(dateReturn);
    }
}

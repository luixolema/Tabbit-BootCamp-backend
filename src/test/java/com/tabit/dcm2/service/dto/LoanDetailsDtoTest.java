package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.exception.BeanValidationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;


public class LoanDetailsDtoTest {
    protected static final LocalDate NOW = LocalDate.now();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void invalid_dto_with_return_date_before_out_date_throws_exception() {
        expectedException.expect(BeanValidationException.class);
        expectedException.expectMessage("LoanDetailsDto.dateReturn: must be after dateOut");
        RandomLoanDetailsDto.createRandomLoanDetailsDtoBuilder().withDateReturn(NOW.minusDays(1)).withDateOut(NOW).build();
    }
}
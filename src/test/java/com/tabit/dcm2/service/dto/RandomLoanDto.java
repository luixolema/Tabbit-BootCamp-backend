package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.testutils.ValueProvider;

public class RandomLoanDto {
    public static LoanDto createRandomLoanDto() {
        ValueProvider valueProvider = new ValueProvider();
        LoanDto loanDto = new LoanDto();

        loanDto.setId(valueProvider.randomId());
        loanDto.setType(valueProvider.randomString("equipment type"));
        loanDto.setSerial(valueProvider.randomString("equipment serial"));
        loanDto.setDateReturn(valueProvider.randomLocalDate());
        loanDto.setDateOut(valueProvider.randomLocalDate());

        return loanDto;
    }
}

package com.tabit.dcm2.testutils;

import com.tabit.dcm2.entity.Loan;
import com.tabit.dcm2.service.dto.LoanDetailsDto;

import static org.assertj.core.api.Assertions.assertThat;

public class LoanMappingAssertions {


    public static void assertLoanDto(LoanDetailsDto loanDetailsDto, Loan loan) {
        assertThat(loanDetailsDto.getId()).isEqualTo(loan.getId());
        assertThat(loanDetailsDto.getDateOut()).isEqualTo(loan.getDateOut());
        assertThat(loanDetailsDto.getDateReturn()).isEqualTo(loan.getDateReturn());
        assertThat(loanDetailsDto.getSerialNumber()).isEqualTo(loan.getEquipment().getSerialNumber());
        assertThat(loanDetailsDto.getType()).isEqualTo(loan.getEquipment().getEquipmentType().getType());
    }
}

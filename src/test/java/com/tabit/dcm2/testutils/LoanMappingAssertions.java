package com.tabit.dcm2.testutils;

import com.tabit.dcm2.entity.Equipment;
import com.tabit.dcm2.entity.EquipmentType;
import com.tabit.dcm2.entity.Loan;
import com.tabit.dcm2.service.dto.LoanDto;

import static org.assertj.core.api.Assertions.assertThat;

public class LoanMappingAssertions {
    public static void assertLoanDto(LoanDto loanDto, Loan loan) {
        Equipment equipment = loan.getEquipment();
        EquipmentType equipmentType = equipment.getEquipmentType();

        assertThat(loanDto.getId()).isEqualTo(loan.getId());
        assertThat(loanDto.getDateOut()).isEqualTo(loan.getDateOut());
        assertThat(loanDto.getDateReturn()).isEqualTo(loan.getDateReturn());
        assertThat(loanDto.getSerial()).isEqualTo(equipment.getSerialNumber());
        assertThat(loanDto.getType()).isEqualTo(equipmentType.getType());
    }
}

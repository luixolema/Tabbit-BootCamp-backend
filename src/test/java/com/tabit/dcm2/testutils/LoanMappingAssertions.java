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


    public static void assertLoan(Loan actualLoan, Loan expectedLoan) {
        Equipment expectedEquipment = expectedLoan.getEquipment();
        EquipmentType expectedEquipmentType = expectedEquipment.getEquipmentType();

        assertThat(actualLoan.getId()).isEqualTo(expectedLoan.getId());
        assertThat(actualLoan.getDateOut()).isEqualTo(expectedLoan.getDateOut());
        assertThat(actualLoan.getDateReturn()).isEqualTo(expectedLoan.getDateReturn());
        assertThat(actualLoan.getEquipment().getSerialNumber()).isEqualTo(expectedEquipment.getSerialNumber());
        assertThat(actualLoan.getEquipment().getEquipmentType().getType()).isEqualTo(expectedEquipmentType.getType());
    }
}

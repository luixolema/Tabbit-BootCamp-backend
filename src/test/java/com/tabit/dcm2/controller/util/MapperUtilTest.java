package com.tabit.dcm2.controller.util;


import com.tabit.dcm2.entity.Equipment;
import com.tabit.dcm2.entity.EquipmentType;
import com.tabit.dcm2.entity.Loan;
import com.tabit.dcm2.entity.RandomLoan;
import com.tabit.dcm2.service.dto.LoanDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MapperUtilTest {

    @Test
    public void mapLoanToLoanDto_should_match_all_fields() {
        //given
        Loan loan = RandomLoan.createRandomLoan();
        Equipment equipment = loan.getEquipment();
        EquipmentType equipmentType = equipment.getEquipmentType();

        //when
        LoanDto loanDto = MapperUtil.mapLoanToLoanDto(loan);

        //then
        assertThat(loanDto.getId()).isEqualTo(loanDto.getId());
        assertThat(loanDto.getDateOut()).isEqualTo(loan.getDateOut());
        assertThat(loanDto.getDateReturn()).isEqualTo(loan.getDateReturn());
        assertThat(loanDto.getType()).isEqualTo(equipmentType.getType());
        assertThat(loanDto.getSerial()).isEqualTo(equipment.getSerialNumber());
    }

    @Test
    public void mapStayToStayDto_should_match_all_fields() {
        // fixme do this if there is time enough
        //given
        //when
        //then
    }
}
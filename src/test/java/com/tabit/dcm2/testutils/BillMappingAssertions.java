package com.tabit.dcm2.testutils;

import com.tabit.dcm2.entity.Activity;
import com.tabit.dcm2.entity.Bill;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.service.dto.BillDto;

import static org.assertj.core.api.Assertions.assertThat;

public class BillMappingAssertions {
    public static void assertBillDto(BillDto billDto, Bill bill) {
        Stay billStay = bill.getStay();

        assertThat(billDto.getActivities().size()).isEqualTo(billStay.getActivities().size());
        assertThat(billDto.getArriveDate()).isEqualTo(billStay.getArriveDate());
        assertThat(billDto.getLeaveDate()).isEqualTo(billStay.getLeaveDate());
        assertThat(billDto.getBoxNumber()).isEqualTo(billStay.getBoxNumber());
        assertThat(billDto.getCheckInDate()).isEqualTo(billStay.getCheckInDate());
        assertThat(billDto.getCheckOutDate()).isEqualTo(billStay.getCheckOutDate());
        assertThat(billDto.getCity()).isEqualTo(billStay.getCity());
        assertThat(billDto.getCode()).isEqualTo(bill.getCode());
        assertThat(billDto.getCountry()).isEqualTo(billStay.getCountry());
        assertThat(billDto.getFirstName()).isEqualTo(billStay.getFirstName());
        assertThat(billDto.getLastName()).isEqualTo(billStay.getLastName());
        assertThat(billDto.getPostcode()).isEqualTo(billStay.getPostcode());
        assertThat(billDto.getStreet()).isEqualTo(billStay.getStreet());
        assertThat(billDto.getTotal()).isEqualTo(billStay.getActivities().stream().mapToDouble(Activity::getPrice).sum());

    }
}

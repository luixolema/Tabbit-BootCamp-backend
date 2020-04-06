package com.tabit.dcm2.testutils;

import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.service.dto.InvoiceDto;

import static org.assertj.core.api.Assertions.assertThat;

public class InvoiceMappingAssertions {
    public static void assertInvoiceDto(InvoiceDto invoiceDto, Stay stay) {
        assertThat(invoiceDto.getFirstName()).isEqualTo(stay.getFirstName());
        assertThat(invoiceDto.getLastName()).isEqualTo(stay.getLastName());
        assertThat(invoiceDto.getCity()).isEqualTo(stay.getCity());
        assertThat(invoiceDto.getCountry()).isEqualTo(stay.getCountry());
        assertThat(invoiceDto.getPostcode()).isEqualTo(stay.getPostcode());
        assertThat(invoiceDto.getArriveDate()).isEqualTo(stay.getArriveDate());
        assertThat(invoiceDto.getBox()).isEqualTo(stay.getBoxNumber());
        assertThat(invoiceDto.getCheckInDate()).isEqualTo(stay.getCheckInDate());
        assertThat(invoiceDto.getCheckOutDate()).isEqualTo(stay.getCheckOutDate());
        assertThat(invoiceDto.getLeaveDate()).isEqualTo(stay.getLeaveDate());
    }
}

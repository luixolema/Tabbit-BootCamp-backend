package com.tabit.dcm2.controller;

import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.RandomGuest;
import com.tabit.dcm2.entity.RandomStay;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.service.dto.InvoiceDto;
import com.tabit.dcm2.service.dto.StayDto;
import com.tabit.dcm2.service.impl.StayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.tabit.dcm2.testutils.InvoiceMappingAssertions.assertInvoiceDto;
import static com.tabit.dcm2.testutils.StayMappingAssertions.assertStayDto;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StayControllerTest {
    @Mock
    private StayService stayService;

    @InjectMocks
    private StayController stayController;

    @Test
    public void getStay_shall_return_stay() {
        // given
        Guest guest = RandomGuest.createRandomGuest();
        Stay randomStay = RandomStay.createRandomStay();
        randomStay.setGuest(guest);
        when(stayService.findById(randomStay.getId())).thenReturn(randomStay);

        // when
        StayDto stayDto = stayController.getStay(randomStay.getId());

        // then
        assertStayDto(stayDto, randomStay);
    }

    @Test
    public void getBillForStay_shall_return_stay() {
        // given
        Guest guest = RandomGuest.createRandomGuest();
        Stay randomStay = RandomStay.createRandomStay();
        randomStay.setGuest(guest);
        when(stayService.findById(randomStay.getId())).thenReturn(randomStay);

        // when
        InvoiceDto invoiceDto = stayController.getBillForStay(randomStay.getId());

        // then
        assertInvoiceDto(invoiceDto, randomStay);
    }
}

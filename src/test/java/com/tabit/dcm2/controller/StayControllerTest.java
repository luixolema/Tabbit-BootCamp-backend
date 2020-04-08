package com.tabit.dcm2.controller;

import com.tabit.dcm2.entity.*;
import com.tabit.dcm2.service.dto.BillDto;
import com.tabit.dcm2.service.dto.StayDto;
import com.tabit.dcm2.service.impl.StayService;
import com.tabit.dcm2.testutils.BillMappingAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

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
    public void getBill_shall_return_billDto() {
        // given
        Bill bill = RandomBill.createRandomBill();

        Stay randomStay = RandomStay.createRandomStay();
        //Random Activities
        List<Activity> activities = new ArrayList<>();

        activities.add(RandomActivity.createRandomActivity());
        activities.add(RandomActivity.createRandomActivity());
        activities.add(RandomActivity.createRandomActivity());
        randomStay.setActivities(activities);

        bill.setStay(randomStay);
        randomStay.setBill(bill);
        when(stayService.findById(randomStay.getId())).thenReturn(randomStay);

        // when
        BillDto billDto = stayController.getBill(randomStay.getId());

        // then
        BillMappingAssertions.assertBillDto(billDto, bill);
    }
}

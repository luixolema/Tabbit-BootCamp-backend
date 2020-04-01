package com.tabit.dcm2.controller;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.RandomGuest;
import com.tabit.dcm2.entity.RandomStay;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.repository.AbstractRepoDbTest;
import com.tabit.dcm2.service.dto.StayDto;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.tabit.dcm2.testutils.StayMappingAssertions.assertStayDto;

public class StayControllerIntegrationTest extends AbstractRepoDbTest {
    @Autowired
    private StayController stayController;

    private Stay stay;

    @Before
    public void setUp() {
        // given
        stay = RandomStay.createRandomStayWithoutId();
        Guest guest = RandomGuest.createRandomGuestWitoutId();
        guest.addStays(ImmutableList.of(stay));

        guestRule.persist(ImmutableList.of(guest));
    }

    @Test
    public void getStay_shall_return_stay() {
        // when
        StayDto stayDto = stayController.getStay(stay.getId());

        // then
        assertStayDto(stayDto, stay);
    }
}
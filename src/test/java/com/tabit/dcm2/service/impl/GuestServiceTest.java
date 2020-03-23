package com.tabit.dcm2.service.impl;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.GuestDto;
import com.tabit.dcm2.entity.RandomGuest;
import com.tabit.dcm2.repository.AbstractRepoDbTest;
import com.tabit.dcm2.repository.IGuestRepo;
import com.tabit.dcm2.service.IGuestService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class GuestServiceTest extends AbstractRepoDbTest {
    @Autowired
    private IGuestRepo guestRepo;
    @Autowired
    private IGuestService guestService;

    @Before
    public void setUp() {
        // given
        Guest guestCheckedinTrue = RandomGuest.createRandomGuestWitoutId();
        guestCheckedinTrue.setCheckedin(true);
        Guest guestCheckedinFalse = RandomGuest.createRandomGuestWitoutId();
        guestCheckedinFalse.setCheckedin(false);
        Guest guestCheckedinFalse2 = RandomGuest.createRandomGuestWitoutId();
        guestCheckedinFalse2.setCheckedin(false);

        guestRule.persist(ImmutableList.of(guestCheckedinTrue, guestCheckedinFalse, guestCheckedinFalse2));
    }

    @Test
    public void getGuests_shall_return_all_guests_for_null_input_param() {
        List<GuestDto> guestDtos = guestService.getGuests(null);

        assertEquals(guestDtos.size(), 3);
    }

    @Test
    public void getGuests_shall_return_checkedin_guests() {
        List<GuestDto> guestDtos = guestService.getGuests(true);

        assertEquals(guestDtos.size(), 1);
    }

    @Test
    public void getGuests_shall_return_not_checkedin_guests() {
        List<GuestDto> guestDtos = guestService.getGuests(false);

        assertEquals(guestDtos.size(), 2);
    }
}
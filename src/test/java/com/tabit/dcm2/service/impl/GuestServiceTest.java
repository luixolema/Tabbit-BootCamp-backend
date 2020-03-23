package com.tabit.dcm2.service.impl;

import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.RandomGuest;
import com.tabit.dcm2.mapper.GuestToGuestDtoMapper;
import com.tabit.dcm2.mapper.IMapperFactory;
import com.tabit.dcm2.repository.IGuestRepo;
import com.tabit.dcm2.service.GuestDto;
import com.tabit.dcm2.service.GuestsDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GuestServiceTest {
    @InjectMocks
    private GuestService guestService;

    @Mock
    private IGuestRepo guestRepo;

    @Mock
    private IMapperFactory mapperFactory;

    @Before
    public void setUp() {
        Guest guestCheckedinTrue = RandomGuest.createRandomGuestWitoutId();
        guestCheckedinTrue.setCheckedin(true);
        Guest guestCheckedinFalse = RandomGuest.createRandomGuestWitoutId();
        guestCheckedinFalse.setCheckedin(false);
        Guest guestCheckedinFalse2 = RandomGuest.createRandomGuestWitoutId();
        guestCheckedinFalse2.setCheckedin(false);

        List<Guest> guests = Arrays.asList(guestCheckedinTrue,guestCheckedinFalse,guestCheckedinFalse2);
        when(guestRepo.findByCheckedin(true)).thenReturn(guests.stream().filter(Guest::isCheckedin).collect(Collectors.toList()));
        when(guestRepo.findByCheckedin(false)).thenReturn(guests.stream().filter(g -> !g.isCheckedin()).collect(Collectors.toList()));
        when(guestRepo.findAll()).thenReturn(guests);

        try {
            when(mapperFactory.getMapper(Guest.class,GuestDto.class)).thenReturn(new GuestToGuestDtoMapper());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getGuests_shall_return_all_guests_for_null_input_param() {
        GuestsDto guestsDto = guestService.getGuests(null);

        assertEquals(guestsDto.getTotal(), 3);
    }

    @Test
    public void getGuests_shall_return_checkedin_guests() {
        GuestsDto guestsDto = guestService.getGuests(true);

        assertEquals(guestsDto.getTotal(), 1);
    }

    @Test
    public void getGuests_shall_return_not_checkedin_guests() {
        GuestsDto guestsDto = guestService.getGuests(false);

        assertEquals(guestsDto.getTotal(), 2);
    }
}
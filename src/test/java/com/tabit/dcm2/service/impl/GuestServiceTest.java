package com.tabit.dcm2.service.impl;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.RandomGuest;
import com.tabit.dcm2.repository.IGuestRepo;
import com.tabit.dcm2.service.GuestFilterType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GuestServiceTest {
    @Mock
    private IGuestRepo guestRepo;
    @InjectMocks
    private GuestService guestService;

    @Test
    public void getGuests_shall_return_all_guests() {
        // given
        Guest randomGuest1 = RandomGuest.createRandomGuest();
        Guest randomGuest2 = RandomGuest.createRandomGuest();
        when(guestRepo.findAll()).thenReturn(ImmutableList.of(randomGuest1, randomGuest2));

        // when
        List<Guest> guests = guestService.getAllGuests(GuestFilterType.ALL);

        // then
        assertThat(guests).containsExactly(randomGuest1, randomGuest2);
    }

    @Test
    public void getGuests_shall_return_checkedin_guests() {
        // given
        Guest randomGuest = RandomGuest.createRandomGuest();
        when(guestRepo.findByCheckedin(true)).thenReturn(ImmutableList.of(randomGuest));

        // when
        List<Guest> guests = guestService.getAllGuests(GuestFilterType.CHECKED_IN);

        // then
        assertThat(guests).containsExactly(randomGuest);
    }

    @Test
    public void getGuests_shall_return_not_checkedin_guests() {
        // given
        Guest randomGuest = RandomGuest.createRandomGuest();
        when(guestRepo.findByCheckedin(false)).thenReturn(ImmutableList.of(randomGuest));

        // when
        List<Guest> guests = guestService.getAllGuests(GuestFilterType.NOT_CHECKED_IN);

        // then
        assertThat(guests).containsExactly(randomGuest);
    }

    @Test
    public void getGuestById_shall_return_guest() {
        // given
        Guest randomGuest = RandomGuest.createRandomGuest();
        when(guestRepo.findById(randomGuest.getId())).thenReturn(java.util.Optional.of(randomGuest));

        // when
        Guest guest = guestService.getGuestById(randomGuest.getId());

        // then
        assertThat(guest).isEqualTo(randomGuest);
    }
}
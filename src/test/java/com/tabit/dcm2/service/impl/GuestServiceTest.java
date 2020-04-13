package com.tabit.dcm2.service.impl;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.RandomGuest;
import com.tabit.dcm2.entity.RandomStay;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.repository.IGuestRepo;
import com.tabit.dcm2.service.GuestFilterType;
import com.tabit.dcm2.service.dto.GuestPersonalDetailsDto;
import com.tabit.dcm2.service.dto.RandomGuestPersonalDetailsDto;
import com.tabit.dcm2.service.util.GuestMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GuestServiceTest {
    @Mock
    private IGuestRepo guestRepo;
    @Mock
    private GuestMapper guestMapper;
    @InjectMocks
    private GuestService guestService;
    @Mock
    private StayService stayService;

    @Captor
    private ArgumentCaptor<Guest> guestArgumentCaptor;

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
        when(guestRepo.findById(randomGuest.getId())).thenReturn(Optional.of(randomGuest));

        // when
        Guest guest = guestService.getGuestById(randomGuest.getId());

        // then
        assertThat(guest).isEqualTo(randomGuest);
    }

    @Test
    public void updatePersonalDetails_shall_update_guest() {
        // given
        Guest randomGuest = RandomGuest.createRandomGuest();
        GuestPersonalDetailsDto guestPersonalDetailsDto = RandomGuestPersonalDetailsDto.createRandomGuestPersonalDetailsDto();

        when(guestRepo.findById(guestPersonalDetailsDto.getId())).thenReturn(Optional.of(randomGuest));

        // when
        guestService.updatePersonalDetails(guestPersonalDetailsDto);

        // then
        verify(guestMapper).mapPersonalDetailsFromDto(randomGuest, guestPersonalDetailsDto);
        verify(guestRepo).save(randomGuest);
    }

    @Test
    // Fixme: should this test verify that only that the stayService was called or also that the stay was update correctly
    public void updatePersonalDetails_shall_update_guest_and_stay_when_guest_is_checkedIn() {
        // given
        Guest randomGuest = RandomGuest.createRandomGuest();
        Stay randomStay = RandomStay.createRandomStay();
        randomGuest.setCheckedin(true);
        randomGuest.setStays(ImmutableList.of(randomStay));
        GuestPersonalDetailsDto guestPersonalDetailsDto = RandomGuestPersonalDetailsDto.createRandomGuestPersonalDetailsDto();

        when(guestRepo.findById(guestPersonalDetailsDto.getId())).thenReturn(Optional.of(randomGuest));

        // when
        guestService.updatePersonalDetails(guestPersonalDetailsDto);

        // then
        verify(guestMapper).mapPersonalDetailsFromDto(randomGuest, guestPersonalDetailsDto);
        verify(guestRepo).save(randomGuest);
        verify(stayService).updatePersonalDetails(randomStay, guestPersonalDetailsDto);
    }
}
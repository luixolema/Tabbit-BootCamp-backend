package com.tabit.dcm2.service.impl;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.RandomGuest;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.exception.GuestIllegalStateException;
import com.tabit.dcm2.repository.IGuestRepo;
import com.tabit.dcm2.repository.IStayRepo;
import com.tabit.dcm2.service.GuestFilterType;
import com.tabit.dcm2.service.IBoxManagementService;
import com.tabit.dcm2.service.dto.CheckInDto;
import com.tabit.dcm2.service.dto.GuestPersonalDetailsDto;
import com.tabit.dcm2.service.dto.RandomCheckInDto;
import com.tabit.dcm2.service.dto.RandomGuestPersonalDetailsDto;
import com.tabit.dcm2.service.util.GuestMapper;
import com.tabit.dcm2.testutils.StayMappingAssertions;
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
    @Mock
    private IBoxManagementService boxManagementService;
    @Mock
    private IStayRepo stayRepo;
    @Captor
    private ArgumentCaptor<Stay> stayArgumentCaptor;
    @InjectMocks
    private GuestService guestService;

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
    public void checkIn_shall_update_guest_and_add_new_active_stay() {
        // given
        CheckInDto randomCheckInDto = RandomCheckInDto.createRandomCheckInDto();
        Guest notCheckedInGuest = RandomGuest.createRandomGuest();
        notCheckedInGuest.setCheckedin(false);
        Stay oldStay = Iterables.getOnlyElement(notCheckedInGuest.getStays());

        when(guestRepo.findById(randomCheckInDto.getGuestPersonalDetails().getId())).thenReturn(Optional.of(notCheckedInGuest));

        // when
        guestService.checkIn(randomCheckInDto);

        // then
        verify(guestMapper).mapPersonalDetailsFromDto(notCheckedInGuest, randomCheckInDto.getGuestPersonalDetails());

        verify(guestRepo).save(notCheckedInGuest);
        assertThat(notCheckedInGuest.isCheckedin()).isTrue();

        verify(stayRepo).save(stayArgumentCaptor.capture());
        Stay newStay = stayArgumentCaptor.getValue();
        StayMappingAssertions.assertNewStayFromCheckInDto(newStay, randomCheckInDto);
        assertThat(notCheckedInGuest.getStays()).containsExactly(oldStay, newStay);

        verify(boxManagementService).reserveBox(randomCheckInDto.getStayDetails().getBoxNumber());
    }

    @Test(expected = GuestIllegalStateException.class)
    public void checkIn_shall_throw_exception_if_guest_is_already_checked_in() {
        // given
        CheckInDto randomCheckInDto = RandomCheckInDto.createRandomCheckInDto();
        Guest checkedInGuest = RandomGuest.createRandomGuest();
        checkedInGuest.setCheckedin(true);

        when(guestRepo.findById(randomCheckInDto.getGuestPersonalDetails().getId())).thenReturn(Optional.of(checkedInGuest));

        // when
        guestService.checkIn(randomCheckInDto);
    }
}
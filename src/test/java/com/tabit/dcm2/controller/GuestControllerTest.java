package com.tabit.dcm2.controller;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.RandomGuest;
import com.tabit.dcm2.service.GuestFilterType;
import com.tabit.dcm2.service.IGuestService;
import com.tabit.dcm2.service.dto.*;
import com.tabit.dcm2.testutils.GuestMappingAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static com.tabit.dcm2.testutils.GuestMappingAssertions.GuestDetailType.WITH_PERSONAL_AND_ACTUAL_STAY_AND_SUMMARY;
import static com.tabit.dcm2.testutils.GuestMappingAssertions.GuestDetailType.WITH_PERSONAL_AND_NO_ACTUAL_STAY_AND_NO_SUMMARY;
import static com.tabit.dcm2.testutils.GuestMappingAssertions.GuestDetailType.WITH_PERSONAL_AND_NO_ACTUAL_STAY_AND_OLD_SUMMARY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GuestControllerTest {

    @Mock
    private IGuestService guestService;

    @InjectMocks
    private GuestController guestController;

    @Test
    public void getGuests_shall_return_all_guests() {
        // given
        Guest randomGuest1 = RandomGuest.createRandomGuest();
        Guest randomGuest2 = RandomGuest.createRandomGuest();
        when(guestService.getAllGuests(GuestFilterType.ALL)).thenReturn(ImmutableList.of(randomGuest1, randomGuest2));

        // when
        GuestOverviewDto guestOverviewDto = guestController.getGuests(2);

        // then
        List<GuestDto> expectedGuestDtos = guestOverviewDto.getGuests();

        assertThat(expectedGuestDtos).hasSize(2);
        GuestMappingAssertions.assertGuestDto(expectedGuestDtos.get(0), randomGuest1, randomGuest1.isCheckedin());
        GuestMappingAssertions.assertGuestDto(expectedGuestDtos.get(1), randomGuest2, randomGuest2.isCheckedin());
        assertThat(guestOverviewDto.getTotal()).isEqualTo(2);
    }

    @Test
    public void getGuests_shall_return_checkedin_guests() {
        // given
        Guest randomGuest = RandomGuest.createRandomGuest();
        randomGuest.setCheckedin(true);
        when(guestService.getAllGuests(GuestFilterType.CHECKED_IN)).thenReturn(ImmutableList.of(randomGuest));

        // when
        GuestOverviewDto guestOverviewDto = guestController.getGuests(1);

        // then
        List<GuestDto> expectedGuestDtos = guestOverviewDto.getGuests();

        assertThat(expectedGuestDtos).hasSize(1);
        GuestMappingAssertions.assertGuestDto(expectedGuestDtos.get(0), randomGuest, true);
        assertThat(guestOverviewDto.getTotal()).isEqualTo(1);
    }

    @Test
    public void getGuests_shall_return_not_checkedin_guests() {
        // given
        Guest randomGuest = RandomGuest.createRandomGuest();
        randomGuest.setCheckedin(false);
        when(guestService.getAllGuests(GuestFilterType.NOT_CHECKED_IN)).thenReturn(ImmutableList.of(randomGuest));

        // when
        GuestOverviewDto guestOverviewDto = guestController.getGuests(0);

        // then
        List<GuestDto> expectedGuestDtos = guestOverviewDto.getGuests();

        assertThat(expectedGuestDtos).hasSize(1);
        GuestMappingAssertions.assertGuestDto(expectedGuestDtos.get(0), randomGuest, false);
        assertThat(guestOverviewDto.getTotal()).isEqualTo(1);
    }

    @Test
    public void getGuestDetails_shall_return_stay_and_personal_details_from_actual_stay_for_checkedIn_guest() {
        // given
        Guest randomGuest = RandomGuest.createRandomGuest();
        randomGuest.setCheckedin(true);
        when(guestService.getGuestById(randomGuest.getId())).thenReturn(randomGuest);

        // when
        GuestDetailDto guestDetailDto = guestController.getGuestDetails(randomGuest.getId());

        // then
        GuestMappingAssertions.assertGuestDetailDto(guestDetailDto, randomGuest, WITH_PERSONAL_AND_ACTUAL_STAY_AND_SUMMARY);
    }

    @Test
    public void getGuestDetails_shall_return_only_personal_details_from_guest_for_not_checkedIn_guest() {
        // given
        Guest randomGuest = RandomGuest.createRandomGuest();
        randomGuest.setCheckedin(false);
        when(guestService.getGuestById(randomGuest.getId())).thenReturn(randomGuest);

        // when
        GuestDetailDto guestDetailDto = guestController.getGuestDetails(randomGuest.getId());

        // then
        GuestMappingAssertions.assertGuestDetailDto(guestDetailDto, randomGuest, WITH_PERSONAL_AND_NO_ACTUAL_STAY_AND_OLD_SUMMARY);
    }

    @Test
    public void getGuestDetails_shall_return_no_summary_for_not_checkedIn_guest_without_stays() {
        // given
        Guest randomGuest = RandomGuest.createRandomGuest();
        randomGuest.setCheckedin(false);
        randomGuest.setStays(new ArrayList<>());
        when(guestService.getGuestById(randomGuest.getId())).thenReturn(randomGuest);

        // when
        GuestDetailDto guestDetailDto = guestController.getGuestDetails(randomGuest.getId());

        // then
        GuestMappingAssertions.assertGuestDetailDto(guestDetailDto, randomGuest, WITH_PERSONAL_AND_NO_ACTUAL_STAY_AND_NO_SUMMARY);
    }

    @Test
    public void checkIn_shall_checkIn_correctly() {
        // given
        CheckInDto randomCheckInDto = RandomCheckInDto.createRandomCheckInDto();

        // when
        guestController.checkIn(randomCheckInDto);

        // then
        verify(guestService).checkIn(randomCheckInDto);
    }

    @Test
    public void getGuestPersonalDetails_return_the_correct_GuestPersonalDetailDto() {
        // given
        Guest randomGuest = RandomGuest.createRandomGuest();

        // when
        when(guestService.getGuestById(randomGuest.getId())).thenReturn(randomGuest);
        GuestPersonalDetailsDto guestPersonalDetailsDto = guestController.getGuestPersonalDetails(randomGuest.getId());

        // then
        verify(guestService).getGuestById(randomGuest.getId());
        GuestMappingAssertions.assertPersonalDetails(randomGuest, guestPersonalDetailsDto);
    }
}
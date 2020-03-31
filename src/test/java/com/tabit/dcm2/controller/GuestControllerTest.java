package com.tabit.dcm2.controller;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.RandomGuest;
import com.tabit.dcm2.service.GuestFilterType;
import com.tabit.dcm2.service.IGuestService;
import com.tabit.dcm2.service.dto.GuestDto;
import com.tabit.dcm2.service.dto.GuestOverviewDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
        assertGuestDto(expectedGuestDtos.get(0), randomGuest1);
        assertGuestDto(expectedGuestDtos.get(1), randomGuest2);
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
        assertGuestDto(expectedGuestDtos.get(0), randomGuest);
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
        assertGuestDto(expectedGuestDtos.get(0), randomGuest);
        assertThat(guestOverviewDto.getTotal()).isEqualTo(1);
    }

    private void assertGuestDto(GuestDto expectedGuestDto, Guest guest) {
        assertThat(expectedGuestDto.getId()).isEqualTo(guest.getId());
        if (guest.isCheckedin()) {
            assertThat(expectedGuestDto.getBoxNumber()).isEqualTo(guest.getStays().get(0).getBoxNumber());
        } else {
            assertThat(expectedGuestDto.getBoxNumber()).isNull();
        }
        assertThat(expectedGuestDto.getFirstName()).isEqualTo(guest.getFirstName());
        assertThat(expectedGuestDto.getLastName()).isEqualTo(guest.getLastName());
    }
}
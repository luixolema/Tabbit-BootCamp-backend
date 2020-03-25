package com.tabit.dcm2.controller;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.tabit.dcm2.service.*;
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
        GuestDto randomGuestDto1 = RandomGuestDto.createRandomGuestDto();
        GuestDto randomGuestDto2 = RandomGuestDto.createRandomGuestDto();
        when(guestService.getGuests(GuestFilterType.ALL)).thenReturn(new GuestsDto(ImmutableList.of(randomGuestDto1, randomGuestDto2)));

        // when
        GuestsDto guestsDto = guestController.getGuests(2);

        // then
        List<GuestDto> expectedGuestDtos = guestsDto.getGuests();

        assertThat(expectedGuestDtos).hasSize(2);
        assertGuestDto(expectedGuestDtos.get(0), randomGuestDto1);
        assertGuestDto(expectedGuestDtos.get(1), randomGuestDto2);
        assertThat(guestsDto.getTotal()).isEqualTo(2);
    }

    @Test
    public void getGuests_shall_return_checkedin_guests() {
        // given
        GuestDto randomGuestDto = RandomGuestDto.createRandomGuestDto();
        when(guestService.getGuests(GuestFilterType.CHECKED_IN)).thenReturn(new GuestsDto(ImmutableList.of(randomGuestDto)));

        // when
        GuestsDto guestsDto = guestController.getGuests(1);

        // then
        List<GuestDto> expectedGuestDtos = guestsDto.getGuests();

        assertGuestDto(Iterables.getOnlyElement(expectedGuestDtos), randomGuestDto);
        assertThat(guestsDto.getTotal()).isEqualTo(1);
    }

    @Test
    public void getGuests_shall_return_not_checkedin_guests() {
        // given
        GuestDto randomGuestDto = RandomGuestDto.createRandomGuestDto();
        when(guestService.getGuests(GuestFilterType.NOT_CHECKED_IN)).thenReturn(new GuestsDto(ImmutableList.of(randomGuestDto)));

        // when
        GuestsDto guestsDto = guestController.getGuests(0);

        // then
        List<GuestDto> expectedGuestDtos = guestsDto.getGuests();

        assertGuestDto(Iterables.getOnlyElement(expectedGuestDtos), randomGuestDto);
        assertThat(guestsDto.getTotal()).isEqualTo(1);
    }

    private void assertGuestDto(GuestDto expectedGuestDto, GuestDto givenGuestDto) {
        assertThat(expectedGuestDto.getId()).isEqualTo(givenGuestDto.getId());
        assertThat(expectedGuestDto.getBoxId()).isEqualTo(givenGuestDto.getBoxId());
        assertThat(expectedGuestDto.getFirstName()).isEqualTo(givenGuestDto.getFirstName());
        assertThat(expectedGuestDto.getLastName()).isEqualTo(givenGuestDto.getLastName());
    }
}
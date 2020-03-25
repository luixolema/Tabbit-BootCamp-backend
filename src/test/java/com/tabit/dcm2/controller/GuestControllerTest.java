package com.tabit.dcm2.controller;

import com.tabit.dcm2.service.GuestFilterType;
import com.tabit.dcm2.service.GuestsDto;
import com.tabit.dcm2.service.IGuestService;
import com.tabit.dcm2.service.RandomGuestsDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
        GuestsDto randomGuestsDto = RandomGuestsDto.createRandomGuestsDto();
        when(guestService.getGuests(GuestFilterType.ALL)).thenReturn(randomGuestsDto);

        // when
        GuestsDto guestsDto = guestController.getGuests(2);

        // then
        assertThat(guestsDto).isEqualTo(randomGuestsDto);
    }

    @Test
    public void getGuests_shall_return_checkedin_guests() {
        // given
        GuestsDto randomGuestsDto = RandomGuestsDto.createRandomGuestsDto();
        when(guestService.getGuests(GuestFilterType.CHECKED_IN)).thenReturn(randomGuestsDto);

        // when
        GuestsDto guestsDto = guestController.getGuests(1);

        // then
        assertThat(guestsDto).isEqualTo(randomGuestsDto);
    }

    @Test
    public void getGuests_shall_return_not_checkedin_guests() {
        // given
        GuestsDto randomGuestsDto = RandomGuestsDto.createRandomGuestsDto();
        when(guestService.getGuests(GuestFilterType.NOT_CHECKED_IN)).thenReturn(randomGuestsDto);

        // when
        GuestsDto guestsDto = guestController.getGuests(0);

        // then
        assertThat(guestsDto).isEqualTo(randomGuestsDto);
    }
}
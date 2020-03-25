package com.tabit.dcm2.service.impl;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.RandomGuest;
import com.tabit.dcm2.repository.IGuestRepo;
import com.tabit.dcm2.service.GuestDto;
import com.tabit.dcm2.service.GuestFilterType;
import com.tabit.dcm2.service.GuestsDto;
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
        Guest randomGuestWithBox = RandomGuest.createRandomGuest();
        Guest randomGuestWitoutBox = RandomGuest.createRandomGuest();
        when(guestRepo.findAll()).thenReturn(ImmutableList.of(randomGuestWithBox, randomGuestWitoutBox));

        // when
        GuestsDto guestsDto = guestService.getGuests(GuestFilterType.ALL);

        // then
        List<GuestDto> expectedGuestDtos = guestsDto.getGuests();

        assertThat(expectedGuestDtos).hasSize(2);
        assertGuestDto(expectedGuestDtos.get(0), randomGuestWithBox);
        assertGuestDto(expectedGuestDtos.get(1), randomGuestWitoutBox);
        assertThat(guestsDto.getTotal()).isEqualTo(2);
    }

    private void assertGuestDto(GuestDto expectedGuestDto, Guest randomGuestWithBox) {
        assertThat(expectedGuestDto.getId()).isEqualTo(randomGuestWithBox.getId());
        assertThat(expectedGuestDto.getBoxId()).isEqualTo(randomGuestWithBox.getBoxId());
        assertThat(expectedGuestDto.getFirstName()).isEqualTo(randomGuestWithBox.getFirstName());
        assertThat(expectedGuestDto.getLastName()).isEqualTo(randomGuestWithBox.getLastName());
    }

    @Test
    public void getGuests_shall_return_checkedin_guests() {
        // given
        Guest randomGuestWithBox = RandomGuest.createRandomGuest();
        when(guestRepo.findByCheckedin(true)).thenReturn(ImmutableList.of(randomGuestWithBox));

        // when
        GuestsDto guestsDto = guestService.getGuests(GuestFilterType.CHECKED_IN);

        // then
        List<GuestDto> expectedGuestDtos = guestsDto.getGuests();

        assertGuestDto(Iterables.getOnlyElement(expectedGuestDtos), randomGuestWithBox);
        assertThat(guestsDto.getTotal()).isEqualTo(1);
    }

    @Test
    public void getGuests_shall_return_not_checkedin_guests() {
        // given
        Guest randomGuestWithBox = RandomGuest.createRandomGuest();
        when(guestRepo.findByCheckedin(false)).thenReturn(ImmutableList.of(randomGuestWithBox));

        // when
        GuestsDto guestsDto = guestService.getGuests(GuestFilterType.NOT_CHECKED_IN);

        // then
        List<GuestDto> expectedGuestDtos = guestsDto.getGuests();

        assertGuestDto(Iterables.getOnlyElement(expectedGuestDtos), randomGuestWithBox);
        assertThat(guestsDto.getTotal()).isEqualTo(1);
    }
}
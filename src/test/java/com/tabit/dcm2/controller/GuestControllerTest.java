package com.tabit.dcm2.controller;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.RandomGuest;
import com.tabit.dcm2.service.GuestDto;
import com.tabit.dcm2.service.GuestFilterType;
import com.tabit.dcm2.service.GuestOverviewDto;
import com.tabit.dcm2.service.IGuestService;
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
        Guest randomGuestWithBox = RandomGuest.createRandomGuest();
        Guest randomGuestWithoutBox = RandomGuest.createRandomGuest();
        when(guestService.getGuests(GuestFilterType.ALL)).thenReturn(ImmutableList.of(randomGuestWithBox, randomGuestWithoutBox));

        // when
        GuestOverviewDto guestOverviewDto = guestController.getGuests(2);

        // then
        List<GuestDto> expectedGuestDtos = guestOverviewDto.getGuests();

        assertThat(expectedGuestDtos).hasSize(2);
        assertGuestDto(expectedGuestDtos.get(0), randomGuestWithBox);
        assertGuestDto(expectedGuestDtos.get(1), randomGuestWithoutBox);
        assertThat(guestOverviewDto.getTotal()).isEqualTo(2);
    }

    @Test
    public void getGuests_shall_return_checkedin_guests() {
        // given
        Guest randomGuestWithBox = RandomGuest.createRandomGuest();
        when(guestService.getGuests(GuestFilterType.CHECKED_IN)).thenReturn(ImmutableList.of(randomGuestWithBox));

        // when
        GuestOverviewDto guestOverviewDto = guestController.getGuests(1);

        // then
        List<GuestDto> expectedGuestDtos = guestOverviewDto.getGuests();

        assertThat(expectedGuestDtos).hasSize(1);
        assertGuestDto(expectedGuestDtos.get(0), randomGuestWithBox);
        assertThat(guestOverviewDto.getTotal()).isEqualTo(1);
    }

    @Test
    public void getGuests_shall_return_not_checkedin_guests() {
        // given
        Guest randomGuestWithBox = RandomGuest.createRandomGuest();
        when(guestService.getGuests(GuestFilterType.NOT_CHECKED_IN)).thenReturn(ImmutableList.of(randomGuestWithBox));

        // when
        GuestOverviewDto guestOverviewDto = guestController.getGuests(0);

        // then
        List<GuestDto> expectedGuestDtos = guestOverviewDto.getGuests();

        assertThat(expectedGuestDtos).hasSize(1);
        assertGuestDto(expectedGuestDtos.get(0), randomGuestWithBox);
        assertThat(guestOverviewDto.getTotal()).isEqualTo(1);
    }

    private void assertGuestDto(GuestDto expectedGuestDto, Guest randomGuestWithBox) {
        assertThat(expectedGuestDto.getId()).isEqualTo(randomGuestWithBox.getId());
        //TODO: put here logic for getting the box of the last stay
        // assertThat(expectedGuestDto.getBoxId()).isEqualTo(randomGuestWithBox.getBoxId());
        assertThat(expectedGuestDto.getFirstName()).isEqualTo(randomGuestWithBox.getFirstName());
        assertThat(expectedGuestDto.getLastName()).isEqualTo(randomGuestWithBox.getLastName());
    }
}
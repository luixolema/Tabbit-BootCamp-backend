package com.tabit.dcm2.controller;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.RandomGuest;
import com.tabit.dcm2.repository.AbstractRepoDbTest;
import com.tabit.dcm2.repository.IGuestRepo;
import com.tabit.dcm2.service.IGuestService;
import com.tabit.dcm2.service.dto.GuestDto;
import com.tabit.dcm2.service.dto.GuestOverviewDto;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class GuestControllerIntegrationTest extends AbstractRepoDbTest {

    private static final Comparator<GuestDto> SORT_BY_ID = Comparator.comparing(GuestDto::getId);

    @Autowired
    private IGuestRepo guestRepo;
    @Autowired
    private IGuestService guestService;
    @Autowired
    private GuestController guestController;
    private Guest guestCheckedInTrue;
    private Guest guestCheckedInFalse;
    private Guest guestCheckedInFalse2;

    @Before
    public void setUp() {
        // given
        guestCheckedInTrue = RandomGuest.createRandomGuestWitoutId();
        guestCheckedInTrue.setCheckedin(true);
        guestCheckedInFalse = RandomGuest.createRandomGuestWitoutId();
        guestCheckedInFalse.setCheckedin(false);
        guestCheckedInFalse2 = RandomGuest.createRandomGuestWitoutId();
        guestCheckedInFalse2.setCheckedin(false);

        guestRule.persist(ImmutableList.of(guestCheckedInTrue, guestCheckedInFalse, guestCheckedInFalse2));
    }

    @Test
    public void  getGuests_shall_return_all_guests_for_null_input_param() {
        // when
        GuestOverviewDto guestOverviewDto = guestController.getGuests(2);

        // then
        List<GuestDto> sorted = new ArrayList<>(guestOverviewDto.getGuests());
        sorted.sort(SORT_BY_ID);

        assertGuestDto(sorted.get(0), guestCheckedInTrue);
        assertGuestDto(sorted.get(1), guestCheckedInFalse);
        assertGuestDto(sorted.get(2), guestCheckedInFalse2);
        assertThat(guestOverviewDto.getTotal()).isEqualTo(3);
    }

    @Test
    public void getGuests_shall_return_checkedin_guests() {
        // when
        GuestOverviewDto guestOverviewDto = guestController.getGuests(1);

        // then
        assertGuestDto(Iterables.getOnlyElement(guestOverviewDto.getGuests()), guestCheckedInTrue);
        assertThat(guestOverviewDto.getTotal()).isEqualTo(1);
    }

    @Test
    public void getGuests_shall_return_not_checkedin_guests() {
        // when
        GuestOverviewDto guestOverviewDto = guestController.getGuests(0);

        // then
        List<GuestDto> sorted = new ArrayList<>(guestOverviewDto.getGuests());
        sorted.sort(SORT_BY_ID);

        assertGuestDto(sorted.get(0), guestCheckedInFalse);
        assertGuestDto(sorted.get(1), guestCheckedInFalse2);
        assertThat(guestOverviewDto.getTotal()).isEqualTo(2);
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
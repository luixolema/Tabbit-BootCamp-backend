package com.tabit.dcm2.controller;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.RandomGuest;
import com.tabit.dcm2.repository.AbstractRepoDbTest;
import com.tabit.dcm2.repository.IGuestRepo;
import com.tabit.dcm2.service.IGuestService;
import com.tabit.dcm2.service.dto.GuestDetailDto;
import com.tabit.dcm2.service.dto.GuestDto;
import com.tabit.dcm2.service.dto.GuestOverviewDto;
import com.tabit.dcm2.testutils.AssertTestUtil;
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

        AssertTestUtil.assertGuestDto(sorted.get(0), guestCheckedInTrue);
        AssertTestUtil.assertGuestDto(sorted.get(1), guestCheckedInFalse);
        AssertTestUtil.assertGuestDto(sorted.get(2), guestCheckedInFalse2);
        assertThat(guestOverviewDto.getTotal()).isEqualTo(3);
    }

    @Test
    public void getGuests_shall_return_checkedin_guests() {
        // when
        GuestOverviewDto guestOverviewDto = guestController.getGuests(1);

        // then
        AssertTestUtil.assertGuestDto(Iterables.getOnlyElement(guestOverviewDto.getGuests()), guestCheckedInTrue);
        assertThat(guestOverviewDto.getTotal()).isEqualTo(1);
    }

    @Test
    public void getGuests_shall_return_not_checkedin_guests() {
        // when
        GuestOverviewDto guestOverviewDto = guestController.getGuests(0);

        // then
        List<GuestDto> sorted = new ArrayList<>(guestOverviewDto.getGuests());
        sorted.sort(SORT_BY_ID);

        AssertTestUtil.assertGuestDto(sorted.get(0), guestCheckedInFalse);
        AssertTestUtil.assertGuestDto(sorted.get(1), guestCheckedInFalse2);
        assertThat(guestOverviewDto.getTotal()).isEqualTo(2);
    }

    @Test
    public void getGuestDetails_shall_return_for_not_checkedin_guest_stay_details_is_empty() {
        // when
        GuestDetailDto guestDetailDto = guestController.getGuestDetails(guestCheckedInFalse.getId());

        // then
        AssertTestUtil.assertGuestDetailDto(guestDetailDto, guestCheckedInFalse);
    }

    @Test
    public void getGuestDetails_shall_return_for_checkedin_guest_full_information() {
        // when
        GuestDetailDto guestDetailDto = guestController.getGuestDetails(guestCheckedInTrue.getId());

        // then
        AssertTestUtil.assertGuestDetailDto(guestDetailDto, guestCheckedInTrue);
    }

}
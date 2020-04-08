package com.tabit.dcm2.controller;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.RandomGuest;
import com.tabit.dcm2.entity.RandomStay;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.repository.AbstractDbTest;
import com.tabit.dcm2.repository.IGuestRepo;
import com.tabit.dcm2.service.IGuestService;
import com.tabit.dcm2.service.dto.GuestDetailDto;
import com.tabit.dcm2.service.dto.GuestDto;
import com.tabit.dcm2.service.dto.GuestOverviewDto;
import com.tabit.dcm2.testutils.GuestMappingAssertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.tabit.dcm2.testutils.GuestMappingAssertions.GuestDetailType.WITH_PERSONAL_AND_NO_ACTUAL_STAY_AND_NO_SUMMARY;
import static com.tabit.dcm2.testutils.GuestMappingAssertions.GuestDetailType.WITH_PERSONAL_AND_NO_ACTUAL_STAY_AND_OLD_SUMMARY;
import static org.assertj.core.api.Assertions.assertThat;


public class GuestControllerIntegrationTest extends AbstractDbTest {

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
    private Guest guestCheckedInFalseWithoutStay;
    private Stay stayOld;
    private Stay stayActual;

    @Before
    public void setUp() {
        // given
        stayOld = RandomStay.createRandomStayWithoutId();
        stayOld.setArriveDate(LocalDate.now().minusYears(5));
        stayActual = RandomStay.createRandomStayWithoutId();
        stayActual.setArriveDate(LocalDate.now().minusDays(10));

        guestCheckedInTrue = RandomGuest.createRandomGuestWitoutId();
        guestCheckedInTrue.setCheckedin(true);
        guestCheckedInTrue.addStays(ImmutableList.of(stayOld, stayActual));

        guestCheckedInFalse = RandomGuest.createRandomGuestWitoutId();
        guestCheckedInFalse.setCheckedin(false);
        guestCheckedInFalse2 = RandomGuest.createRandomGuestWitoutId();
        guestCheckedInFalse2.setCheckedin(false);
        guestCheckedInFalseWithoutStay = RandomGuest.createRandomGuestWitoutId();
        guestCheckedInFalseWithoutStay.setCheckedin(false);
        guestCheckedInFalseWithoutStay.setStays(new ArrayList<>());

        guestRule.persist(ImmutableList.of(guestCheckedInTrue, guestCheckedInFalse, guestCheckedInFalse2, guestCheckedInFalseWithoutStay));
    }

    @Test
    public void  getGuests_shall_return_all_guests_for_null_input_param() {
        // when
        GuestOverviewDto guestOverviewDto = guestController.getGuests(2);

        // then
        List<GuestDto> sorted = new ArrayList<>(guestOverviewDto.getGuests());
        sorted.sort(SORT_BY_ID);

        GuestMappingAssertions.assertGuestDto(sorted.get(0), guestCheckedInTrue, true);
        GuestMappingAssertions.assertGuestDto(sorted.get(1), guestCheckedInFalse, false);
        GuestMappingAssertions.assertGuestDto(sorted.get(2), guestCheckedInFalse2, false);
        GuestMappingAssertions.assertGuestDto(sorted.get(3), guestCheckedInFalseWithoutStay, false);
        assertThat(guestOverviewDto.getTotal()).isEqualTo(4);
    }

    @Test
    public void getGuests_shall_return_checkedin_guests() {
        // when
        GuestOverviewDto guestOverviewDto = guestController.getGuests(1);

        // then
        GuestMappingAssertions.assertGuestDto(Iterables.getOnlyElement(guestOverviewDto.getGuests()), guestCheckedInTrue, true);
        assertThat(guestOverviewDto.getTotal()).isEqualTo(1);
    }

    @Test
    public void getGuests_shall_return_not_checkedin_guests() {
        // when
        GuestOverviewDto guestOverviewDto = guestController.getGuests(0);

        // then
        List<GuestDto> sorted = new ArrayList<>(guestOverviewDto.getGuests());
        sorted.sort(SORT_BY_ID);

        GuestMappingAssertions.assertGuestDto(sorted.get(0), guestCheckedInFalse, false);
        GuestMappingAssertions.assertGuestDto(sorted.get(1), guestCheckedInFalse2, false);
        GuestMappingAssertions.assertGuestDto(sorted.get(2), guestCheckedInFalseWithoutStay, false);
        assertThat(guestOverviewDto.getTotal()).isEqualTo(3);
    }

    @Test
    public void getGuestDetails_shall_return_only_personal_details_from_guest_for_not_checkedIn_guest() {
        // when
        GuestDetailDto guestDetailDto = guestController.getGuestDetails(guestCheckedInFalse.getId());

        // then
        GuestMappingAssertions.assertGuestDetailDto(guestDetailDto, guestCheckedInFalse, WITH_PERSONAL_AND_NO_ACTUAL_STAY_AND_OLD_SUMMARY);
    }

    @Test
    public void getGuestDetails_shall_return_no_summary_for_not_checkedIn_guest_without_stays() {
        // when
        GuestDetailDto guestDetailDto = guestController.getGuestDetails(guestCheckedInFalseWithoutStay.getId());

        // then
        GuestMappingAssertions.assertGuestDetailDto(guestDetailDto, guestCheckedInFalseWithoutStay, WITH_PERSONAL_AND_NO_ACTUAL_STAY_AND_NO_SUMMARY);
    }
}
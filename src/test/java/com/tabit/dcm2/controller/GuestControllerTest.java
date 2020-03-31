package com.tabit.dcm2.controller;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.RandomGuest;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.service.GuestFilterType;
import com.tabit.dcm2.service.IGuestService;
import com.tabit.dcm2.service.dto.GuestDetailDto;
import com.tabit.dcm2.service.dto.GuestDto;
import com.tabit.dcm2.service.dto.GuestOverviewDto;
import com.tabit.dcm2.service.dto.StayDto;
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

    @Test
    public void getGuestDetails_shall_return_stay_and_personal_details_for_checkedIn_guest() {
        // given
        Guest randomGuest = RandomGuest.createRandomGuest();
        randomGuest.setCheckedin(true);
        when(guestService.getGuestById(randomGuest.getId())).thenReturn(randomGuest);

        // when
        GuestDetailDto guestDetailDto = guestController.getGuestDetails(randomGuest.getId());

        // then
        assertGuestDetailDto(guestDetailDto, randomGuest);
    }

    @Test
    public void getGuestDetails_shall_return_stay_and_personal_details_for_not_checkedIn_guest() {
        // given
        Guest randomGuest = RandomGuest.createRandomGuest();
        randomGuest.setCheckedin(false);
        when(guestService.getGuestById(randomGuest.getId())).thenReturn(randomGuest);

        // when
        GuestDetailDto guestDetailDto = guestController.getGuestDetails(randomGuest.getId());

        // then
        assertGuestDetailDto(guestDetailDto, randomGuest);
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

    private void assertGuestDetailDto(GuestDetailDto expectedGuestDetailDto, Guest guest) {
        StayDto stayDto = expectedGuestDetailDto.getStayDto();
        List<GuestDetailDto.StaySummary> stayHistory = expectedGuestDetailDto.getStaysHistory();
        if (guest.isCheckedin()) {
            Stay stay = guest.getStays().get(0);
            assertThat(stayDto.getId()).isEqualTo(stay.getId());
            assertThat(stayDto.getStayDetails().getArriveDate()).isEqualTo(stay.getArriveDate());
            assertThat(stayDto.getStayDetails().getBoxNumber()).isEqualTo(stay.getBoxNumber());
            assertThat(stayDto.getStayDetails().getBrevet()).isEqualTo(stay.getBrevet());
            assertThat(stayDto.getStayDetails().getCheckInDate()).isEqualTo(stay.getCheckInDate());
            assertThat(stayDto.getStayDetails().getCheckOutDate()).isEqualTo(stay.getCheckOutDate());
            assertThat(stayDto.getStayDetails().getLastDiveDate()).isEqualTo(stay.getLastDiveDate());
            assertThat(stayDto.getStayDetails().getLeaveDate()).isEqualTo(stay.getLeaveDate());
            assertThat(stayDto.getStayDetails().getDivesAmount()).isEqualTo(stay.getDivesAmount());
            assertThat(stayDto.getStayDetails().getHotel()).isEqualTo(stay.getHotel());
            assertThat(stayDto.getStayDetails().getRoom()).isEqualTo(stay.getRoom());
            assertThat(stayDto.getStayDetails().isNitrox()).isEqualTo(stay.isNitrox());
            assertThat(stayDto.getStayDetails().isMedicalStatement()).isEqualTo(stay.isMedicalStatement());

            // only for checkedIn guest personal data must be equal with current stay. For historical stay we can#t guarantee that
            assertThat(stayDto.getGuestPersonalDetails().getGuestId()).isEqualTo(guest.getId());
            assertThat(stayDto.getGuestPersonalDetails().getFirstName()).isEqualTo(guest.getFirstName());
            assertThat(stayDto.getGuestPersonalDetails().getLastName()).isEqualTo(guest.getLastName());
            assertThat(stayDto.getGuestPersonalDetails().getBirthDate()).isEqualTo(guest.getBirthDate());
            assertThat(stayDto.getGuestPersonalDetails().getCity()).isEqualTo(guest.getCity());
            assertThat(stayDto.getGuestPersonalDetails().getCountry()).isEqualTo(guest.getCountry());
            assertThat(stayDto.getGuestPersonalDetails().getEmail()).isEqualTo(guest.getEmail());
            assertThat(stayDto.getGuestPersonalDetails().getNationality()).isEqualTo(guest.getNationality());
            assertThat(stayDto.getGuestPersonalDetails().getPassportId()).isEqualTo(guest.getPassportId());
            assertThat(stayDto.getGuestPersonalDetails().getPhone()).isEqualTo(guest.getPhone());
            assertThat(stayDto.getGuestPersonalDetails().getPostcode()).isEqualTo(guest.getPostcode());

            assertThat(stayHistory.size()).isEqualTo(guest.getStays().size());
            assertThat(stayHistory.get(0).getId()).isEqualTo(guest.getStays().get(0).getId());
        } else {
            assertThat(stayDto.getId()).isNull();
            assertThat(stayDto.getStayDetails().getArriveDate()).isNull();
            assertThat(stayDto.getStayDetails().getBoxNumber()).isNull();
            assertThat(stayDto.getStayDetails().getBrevet()).isNull();
            assertThat(stayDto.getStayDetails().getCheckInDate()).isNull();
            assertThat(stayDto.getStayDetails().getCheckOutDate()).isNull();
            assertThat(stayDto.getStayDetails().getLastDiveDate()).isNull();
            assertThat(stayDto.getStayDetails().getLeaveDate()).isNull();
            assertThat(stayDto.getStayDetails().getDivesAmount()).isNull();
            assertThat(stayDto.getStayDetails().getHotel()).isNull();
            assertThat(stayDto.getStayDetails().getRoom()).isNull();
            assertThat(stayDto.getStayDetails().isNitrox()).isFalse();
            assertThat(stayDto.getStayDetails().isMedicalStatement()).isFalse();

            // there should summary with null values first position in List for not checkIn guest
            assertThat(stayHistory.size()).isEqualTo(guest.getStays().size() + 1);
            assertThat(stayHistory.get(0).getId()).isNull();
            assertThat(stayHistory.get(0).getCheckInDate()).isNull();
            assertThat(stayHistory.get(0).getCheckOutDate()).isNull();
        }
    }
}
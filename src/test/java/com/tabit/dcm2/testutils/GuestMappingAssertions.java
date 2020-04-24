package com.tabit.dcm2.testutils;

import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.service.dto.*;
import org.assertj.core.util.Lists;

import java.util.List;

import static com.tabit.dcm2.testutils.GuestMappingAssertions.GuestDetailType.WITH_PERSONAL_AND_ACTUAL_STAY_AND_SUMMARY;
import static com.tabit.dcm2.testutils.GuestMappingAssertions.GuestDetailType.WITH_PERSONAL_AND_NO_ACTUAL_STAY_AND_NO_SUMMARY;
import static java.util.Comparator.comparing;
import static org.assertj.core.api.Assertions.assertThat;

public class GuestMappingAssertions {
    public static void assertGuestDto(GuestDto expectedGuestDto, Guest guest, boolean expectBoxNumber) {
        assertThat(expectedGuestDto.getId()).isEqualTo(guest.getId());
        if (expectBoxNumber) {
            List<Stay> sortedStays = sortStayByArriveDate(guest);
            assertThat(expectedGuestDto.getBoxNumber()).contains(sortedStays.get(0).getBoxNumber());
        } else {
            assertThat(expectedGuestDto.getBoxNumber()).isEmpty();
        }
        assertThat(expectedGuestDto.getFirstName()).isEqualTo(guest.getFirstName());
        assertThat(expectedGuestDto.getLastName()).isEqualTo(guest.getLastName());
    }

    public static void assertGuestDetailDto(GuestDetailDto expectedGuestDetailDto, Guest guest, GuestDetailType guestDetailType) {
        StayDto stayDto = expectedGuestDetailDto.getStayDto();
        List<StaySummaryDto> staySummary = expectedGuestDetailDto.getStaySummaries();
        List<Stay> sortedStays = sortStayByArriveDate(guest);

        if (guestDetailType == WITH_PERSONAL_AND_ACTUAL_STAY_AND_SUMMARY) {
            Stay stay = sortedStays.get(0);
            assertThat(stayDto.getStayDetails().getId()).isEqualTo(stay.getId());
            assertThat(stayDto.getStayDetails().getArriveDate()).isEqualTo(stay.getArriveDate());
            assertThat(stayDto.getStayDetails().getBoxNumber()).isEqualTo(stay.getBoxNumber());
            assertThat(stayDto.getStayDetails().getBrevet()).isEqualTo(stay.getBrevet());
            assertThat(stayDto.getStayDetails().getCheckInDate()).isEqualTo(stay.getCheckInDate());
            assertThat(stayDto.getStayDetails().getCheckOutDate()).contains(stay.getCheckOutDate());
            assertThat(stayDto.getStayDetails().getLastDiveDate()).isEqualTo(stay.getLastDiveDate());
            assertThat(stayDto.getStayDetails().getLeaveDate()).isEqualTo(stay.getLeaveDate());
            assertThat(stayDto.getStayDetails().getDivesAmount()).isEqualTo(stay.getDivesAmount());
            assertThat(stayDto.getStayDetails().getHotel()).isEqualTo(stay.getHotel());
            assertThat(stayDto.getStayDetails().getRoom()).isEqualTo(stay.getRoom());
            assertThat(stayDto.getStayDetails().isNitrox()).isEqualTo(stay.isNitrox());
            assertThat(stayDto.getStayDetails().isMedicalStatement()).isEqualTo(stay.isMedicalStatement());
            assertThat(stayDto.getStayDetails().getPreBooking()).isEqualTo(stay.getPreBooking());

            assertThat(stayDto.getGuestPersonalDetails().getFirstName()).isEqualTo(stay.getFirstName());
            assertThat(stayDto.getGuestPersonalDetails().getLastName()).isEqualTo(stay.getLastName());
            assertThat(stayDto.getGuestPersonalDetails().getBirthDate()).isEqualTo(stay.getBirthDate());
            assertThat(stayDto.getGuestPersonalDetails().getCity()).isEqualTo(stay.getCity());
            assertThat(stayDto.getGuestPersonalDetails().getCountry()).isEqualTo(stay.getCountry());
            assertThat(stayDto.getGuestPersonalDetails().getEmail()).isEqualTo(stay.getEmail());
            assertThat(stayDto.getGuestPersonalDetails().getNationality()).isEqualTo(stay.getNationality());
            assertThat(stayDto.getGuestPersonalDetails().getPassportId()).isEqualTo(stay.getPassportId());
            assertThat(stayDto.getGuestPersonalDetails().getPhone()).isEqualTo(stay.getPhone());
            assertThat(stayDto.getGuestPersonalDetails().getPostcode()).isEqualTo(stay.getPostcode());
            assertThat(stayDto.getGuestPersonalDetails().getId()).isEqualTo(stay.getGuest().getId());

            assertStaySummaries(staySummary, sortedStays, guestDetailType);
        } else {
            assertThat(stayDto.getStayDetails()).isNull();

            assertThat(stayDto.getGuestPersonalDetails().getId()).isEqualTo(guest.getId());
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

            assertStaySummaries(staySummary, sortedStays, guestDetailType);
        }
    }

    private static List<Stay> sortStayByArriveDate(Guest guest) {
        List<Stay> sortedStays = Lists.newArrayList(guest.getStays());
        sortedStays.sort(comparing(Stay::getArriveDate).reversed());
        return sortedStays;
    }

    private static void assertStaySummaries(List<StaySummaryDto> staySummaries, List<Stay> sortedStays, GuestDetailType guestDetailType) {

        if (guestDetailType == WITH_PERSONAL_AND_NO_ACTUAL_STAY_AND_NO_SUMMARY) {
            assertThat(staySummaries).isEmpty();
        } else {
            assertThat(staySummaries).hasSize(sortedStays.size());
            for (int i = 0; i < staySummaries.size(); i++) {
                StaySummaryDto staySummaryDto = staySummaries.get(i);
                Stay stay = sortedStays.get(i);

                assertThat(staySummaryDto.getId()).isEqualTo(stay.getId());
                assertThat(staySummaryDto.getArriveDate()).isEqualTo(stay.getArriveDate());
                assertThat(staySummaryDto.getLeaveDate()).isEqualTo(stay.getLeaveDate());
                assertThat(staySummaryDto.isActive()).isEqualTo(stay.isActive());
            }
        }
    }

    public static void assertPersonalDetails(Guest guest, AbstractGuestPersonalDetailsDto guestPersonalDetailsDto) {
        assertThat(guest.getFirstName()).isEqualTo(guestPersonalDetailsDto.getFirstName());
        assertThat(guest.getLastName()).isEqualTo(guestPersonalDetailsDto.getLastName());
        assertThat(guest.getBirthDate()).isEqualTo(guestPersonalDetailsDto.getBirthDate());
        assertThat(guest.getEmail()).isEqualTo(guestPersonalDetailsDto.getEmail());
        assertThat(guest.getNationality()).isEqualTo(guestPersonalDetailsDto.getNationality());
        assertThat(guest.getCity()).isEqualTo(guestPersonalDetailsDto.getCity());
        assertThat(guest.getPassportId()).isEqualTo(guestPersonalDetailsDto.getPassportId());
        assertThat(guest.getPhone()).isEqualTo(guestPersonalDetailsDto.getPhone());
        assertThat(guest.getCountry()).isEqualTo(guestPersonalDetailsDto.getCountry());
        assertThat(guest.getPostcode()).isEqualTo(guestPersonalDetailsDto.getPostcode());
        assertThat(guest.getStreet()).isEqualTo(guestPersonalDetailsDto.getStreet());
    }

    public enum GuestDetailType {
        WITH_PERSONAL_AND_ACTUAL_STAY_AND_SUMMARY,
        WITH_PERSONAL_AND_NO_ACTUAL_STAY_AND_NO_SUMMARY,
        WITH_PERSONAL_AND_NO_ACTUAL_STAY_AND_OLD_SUMMARY,
    }
}

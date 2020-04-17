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
            assertThat(expectedGuestDto.getBoxNumber()).isEqualTo(sortedStays.get(0).getBoxNumber());
        } else {
            assertThat(expectedGuestDto.getBoxNumber()).isNull();
        }
        assertThat(expectedGuestDto.getFirstName()).isEqualTo(guest.getFirstName());
        assertThat(expectedGuestDto.getLastName()).isEqualTo(guest.getLastName());
    }

    public static void assertGuestDetailDto(GuestDetailDto expectedGuestDetailDto, Guest guest, GuestDetailType guestDetailType) {
        CompleteStayDto completeStayDto = expectedGuestDetailDto.getStayDto();
        List<StaySummaryDto> staySummary = expectedGuestDetailDto.getStaySummaries();
        List<Stay> sortedStays = sortStayByArriveDate(guest);

        if (guestDetailType == WITH_PERSONAL_AND_ACTUAL_STAY_AND_SUMMARY) {
            Stay stay = sortedStays.get(0);
            StayMappingAssertions.assertCompleteStayDto(completeStayDto, stay);

            assertStaySummaries(staySummary, sortedStays, guestDetailType);
        } else {
            assertThat(completeStayDto.getStayDetails()).isNull();

            assertThat(completeStayDto.getGuestPersonalDetails().getId()).isEqualTo(guest.getId());
            assertThat(completeStayDto.getGuestPersonalDetails().getFirstName()).isEqualTo(guest.getFirstName());
            assertThat(completeStayDto.getGuestPersonalDetails().getLastName()).isEqualTo(guest.getLastName());
            assertThat(completeStayDto.getGuestPersonalDetails().getBirthDate()).isEqualTo(guest.getBirthDate());
            assertThat(completeStayDto.getGuestPersonalDetails().getCity()).isEqualTo(guest.getCity());
            assertThat(completeStayDto.getGuestPersonalDetails().getCountry()).isEqualTo(guest.getCountry());
            assertThat(completeStayDto.getGuestPersonalDetails().getEmail()).isEqualTo(guest.getEmail());
            assertThat(completeStayDto.getGuestPersonalDetails().getNationality()).isEqualTo(guest.getNationality());
            assertThat(completeStayDto.getGuestPersonalDetails().getPassportId()).isEqualTo(guest.getPassportId());
            assertThat(completeStayDto.getGuestPersonalDetails().getPhone()).isEqualTo(guest.getPhone());
            assertThat(completeStayDto.getGuestPersonalDetails().getPostcode()).isEqualTo(guest.getPostcode());

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

    public static void assertPersonalDetails(Guest guest, GuestPersonalDetailsDto guestPersonalDetailsDto) {
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

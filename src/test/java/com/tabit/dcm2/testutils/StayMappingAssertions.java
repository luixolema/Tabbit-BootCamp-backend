package com.tabit.dcm2.testutils;

import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.service.dto.StayDto;

import static org.assertj.core.api.Assertions.assertThat;

public class StayMappingAssertions {
    public static void assertStayDto(StayDto stayDto, Stay stay) {
        assertThat(stayDto.getGuestPersonalDetails().getId()).isEqualTo(stay.getGuest().getId());
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
        assertThat(stayDto.getStayDetails().getPreBooking()).isEqualTo(stay.getPreBooking());
    }

    public static void assertTwoStaysWithGuest(Stay oldStay, Stay newStay) {
        assertThat(oldStay.getId()).isEqualTo(newStay.getGuest().getId());
        assertThat(oldStay.getFirstName() + "Update").isEqualTo(newStay.getFirstName());
        assertThat(oldStay.getLastName()).isEqualTo(newStay.getLastName());
        assertThat(oldStay.getBirthDate().minusDays(10)).isEqualTo(newStay.getBirthDate());
        assertThat(oldStay.getCity()).isEqualTo(newStay.getCity());
        assertThat(oldStay.getCountry()).isEqualTo(newStay.getCountry());
        assertThat(oldStay.getEmail()).isEqualTo(newStay.getEmail());
        assertThat(oldStay.getNationality()).isEqualTo(newStay.getNationality());
        assertThat(oldStay.getPassportId()).isEqualTo(newStay.getPassportId());
        assertThat(oldStay.getPhone()).isEqualTo(newStay.getPhone());
        assertThat(oldStay.getPostcode()).isEqualTo(newStay.getPostcode());

        assertThat(oldStay.getArriveDate()).isEqualTo(newStay.getArriveDate());
        assertThat(oldStay.getBoxNumber()).isEqualTo(newStay.getBoxNumber());
        assertThat(oldStay.getBrevet()).isEqualTo(newStay.getBrevet());
        assertThat(oldStay.getCheckInDate()).isEqualTo(newStay.getCheckInDate());
        assertThat(oldStay.getCheckOutDate()).isEqualTo(newStay.getCheckOutDate());
        assertThat(oldStay.getLastDiveDate()).isEqualTo(newStay.getLastDiveDate());
        assertThat(oldStay.getLeaveDate()).isEqualTo(newStay.getLeaveDate());
        assertThat(oldStay.getDivesAmount()).isEqualTo(newStay.getDivesAmount());
        assertThat(oldStay.getHotel() + "Update").isEqualTo(newStay.getHotel());
        assertThat(oldStay.getRoom()).isEqualTo(newStay.getRoom());
        assertThat(oldStay.isNitrox()).isEqualTo(newStay.isNitrox());
        assertThat(oldStay.isMedicalStatement()).isEqualTo(newStay.isMedicalStatement());
        assertThat(oldStay.getPreBooking()).isEqualTo(newStay.getPreBooking());
    }

    public static void assertTwoStaysWithoutGuest(Stay oldStay, Stay newStay) {
        assertThat(oldStay.getId()).isEqualTo(newStay.getGuest().getId());
        assertThat(oldStay.getFirstName()).isEqualTo(newStay.getFirstName());
        assertThat(oldStay.getLastName()).isEqualTo(newStay.getLastName());
        assertThat(oldStay.getBirthDate()).isEqualTo(newStay.getBirthDate());
        assertThat(oldStay.getCity()).isEqualTo(newStay.getCity());
        assertThat(oldStay.getCountry()).isEqualTo(newStay.getCountry());
        assertThat(oldStay.getEmail()).isEqualTo(newStay.getEmail());
        assertThat(oldStay.getNationality()).isEqualTo(newStay.getNationality());
        assertThat(oldStay.getPassportId()).isEqualTo(newStay.getPassportId());
        assertThat(oldStay.getPhone()).isEqualTo(newStay.getPhone());
        assertThat(oldStay.getPostcode()).isEqualTo(newStay.getPostcode());

        assertThat(oldStay.getArriveDate()).isEqualTo(newStay.getArriveDate());
        assertThat(oldStay.getBoxNumber()).isEqualTo(newStay.getBoxNumber());
        assertThat(oldStay.getBrevet()).isEqualTo(newStay.getBrevet());
        assertThat(oldStay.getCheckInDate()).isEqualTo(newStay.getCheckInDate());
        assertThat(oldStay.getCheckOutDate()).isEqualTo(newStay.getCheckOutDate());
        assertThat(oldStay.getLastDiveDate()).isEqualTo(newStay.getLastDiveDate());
        assertThat(oldStay.getLeaveDate()).isEqualTo(newStay.getLeaveDate());
        assertThat(oldStay.getDivesAmount()).isEqualTo(newStay.getDivesAmount());
        assertThat(oldStay.getHotel() + "Update").isEqualTo(newStay.getHotel());
        assertThat(oldStay.getRoom()).isEqualTo(newStay.getRoom());
        assertThat(oldStay.isNitrox()).isEqualTo(newStay.isNitrox());
        assertThat(oldStay.isMedicalStatement()).isEqualTo(newStay.isMedicalStatement());
        assertThat(oldStay.getPreBooking()).isEqualTo(newStay.getPreBooking());
    }
}

package com.tabit.dcm2.testutils;

import com.tabit.dcm2.entity.Loan;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.service.dto.CheckInDto;
import com.tabit.dcm2.service.dto.LoanDetailsDto;
import com.tabit.dcm2.service.dto.StayDto;
import org.assertj.core.util.Lists;

import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;
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
        assertThat(stayDto.getGuestPersonalDetails().getPassportId().orElse(null)).isEqualTo(stay.getPassportId());
        assertThat(stayDto.getGuestPersonalDetails().getPhone().orElse(null)).isEqualTo(stay.getPhone());
        assertThat(stayDto.getGuestPersonalDetails().getPostcode()).isEqualTo(stay.getPostcode());

        assertThat(stayDto.getStayDetails().getArriveDate()).isEqualTo(stay.getArriveDate());
        assertThat(stayDto.getStayDetails().getBoxNumber()).isEqualTo(stay.getBoxNumber());
        assertThat(stayDto.getStayDetails().getBrevet()).isEqualTo(stay.getBrevet());
        assertThat(stayDto.getStayDetails().getCheckInDate()).isEqualTo(stay.getCheckInDate());
        assertThat(stayDto.getStayDetails().getCheckOutDate()).isEqualTo(Optional.ofNullable(stay.getCheckOutDate()));
        assertThat(stayDto.getStayDetails().getLastDiveDate()).isEqualTo(stay.getLastDiveDate());
        assertThat(stayDto.getStayDetails().getLeaveDate()).isEqualTo(stay.getLeaveDate());
        assertThat(stayDto.getStayDetails().getDivesAmount()).isEqualTo(stay.getDivesAmount());
        assertThat(stayDto.getStayDetails().getHotel()).isEqualTo(stay.getHotel());
        assertThat(stayDto.getStayDetails().getRoom()).isEqualTo(stay.getRoom());
        assertThat(stayDto.getStayDetails().isNitrox()).isEqualTo(stay.isNitrox());
        assertThat(stayDto.getStayDetails().isMedicalStatement()).isEqualTo(stay.isMedicalStatement());
        assertThat(stayDto.getStayDetails().getPreBooking()).isEqualTo(stay.getPreBooking());

        List<Loan> sortLoansByDateOut = sortLoansByDateOut(stay);
        assertLoanDetailsDtos(stayDto.getLoanDetails(), sortLoansByDateOut);
    }

    private static List<Loan> sortLoansByDateOut(Stay stay) {
        List<Loan> sortedLoans = Lists.newArrayList(stay.getLoans());
        sortedLoans.sort(comparing(Loan::getDateOut));
        return sortedLoans;
    }

    private static void assertLoanDetailsDtos(List<LoanDetailsDto> loanDetailsDtos, List<Loan> sortedLoans) {
        assertThat(loanDetailsDtos).hasSize(sortedLoans.size());
        for (int i = 0; i < loanDetailsDtos.size(); i++) {
            LoanDetailsDto loanDetailsDto = loanDetailsDtos.get(i);
            Loan loan = sortedLoans.get(i);
            LoanMappingAssertions.assertLoanDto(loanDetailsDto, loan);
        }
    }

    public static void assertNewStayFromCheckInDto(Stay newStay, CheckInDto checkInDto) {
        assertThat(newStay.getFirstName()).isEqualTo(checkInDto.getGuestPersonalDetails().getFirstName());
        assertThat(newStay.getLastName()).isEqualTo(checkInDto.getGuestPersonalDetails().getLastName());
        assertThat(newStay.getBirthDate()).isEqualTo(checkInDto.getGuestPersonalDetails().getBirthDate());
        assertThat(newStay.getNationality()).isEqualTo(checkInDto.getGuestPersonalDetails().getNationality());
        assertThat(newStay.getCountry()).isEqualTo(checkInDto.getGuestPersonalDetails().getCountry());
        assertThat(newStay.getCity()).isEqualTo(checkInDto.getGuestPersonalDetails().getCity());
        assertThat(newStay.getPostcode()).isEqualTo(checkInDto.getGuestPersonalDetails().getPostcode());
        assertThat(newStay.getStreet()).isEqualTo(checkInDto.getGuestPersonalDetails().getStreet());
        assertThat(newStay.getEmail()).isEqualTo(checkInDto.getGuestPersonalDetails().getEmail());
        assertThat(newStay.getPhone()).isEqualTo(checkInDto.getGuestPersonalDetails().getPhone().orElse(null));
        assertThat(newStay.getPassportId()).isEqualTo(checkInDto.getGuestPersonalDetails().getPassportId().orElse(null));
        assertThat(newStay.getBoxNumber()).isEqualTo(checkInDto.getStayDetails().getBoxNumber());
        assertThat(newStay.getCheckInDate()).isEqualTo(checkInDto.getStayDetails().getCheckInDate());
        assertThat(newStay.getArriveDate()).isEqualTo(checkInDto.getStayDetails().getArriveDate());
        assertThat(newStay.getLeaveDate()).isEqualTo(checkInDto.getStayDetails().getLeaveDate());
        assertThat(newStay.getHotel()).isEqualTo(checkInDto.getStayDetails().getHotel());
        assertThat(newStay.getRoom()).isEqualTo(checkInDto.getStayDetails().getRoom());
        assertThat(newStay.getLastDiveDate()).isEqualTo(checkInDto.getStayDetails().getLastDiveDate());
        assertThat(newStay.getBrevet()).isEqualTo(checkInDto.getStayDetails().getBrevet());
        assertThat(newStay.getDivesAmount()).isEqualTo(checkInDto.getStayDetails().getDivesAmount());
        assertThat(newStay.isNitrox()).isEqualTo(checkInDto.getStayDetails().isNitrox());
        assertThat(newStay.isMedicalStatement()).isEqualTo(checkInDto.getStayDetails().isMedicalStatement());
        assertThat(newStay.isActive()).isTrue();
        assertThat(newStay.getPreBooking()).isEqualTo(checkInDto.getStayDetails().getPreBooking());
    }

    public static void assertUpdatedStayFromStayDto(Stay updatedStay, StayDto stayDto) {
        assertThat(updatedStay.getFirstName()).isEqualTo(stayDto.getGuestPersonalDetails().getFirstName());
        assertThat(updatedStay.getLastName()).isEqualTo(stayDto.getGuestPersonalDetails().getLastName());
        assertThat(updatedStay.getBirthDate()).isEqualTo(stayDto.getGuestPersonalDetails().getBirthDate());
        assertThat(updatedStay.getNationality()).isEqualTo(stayDto.getGuestPersonalDetails().getNationality());
        assertThat(updatedStay.getCountry()).isEqualTo(stayDto.getGuestPersonalDetails().getCountry());
        assertThat(updatedStay.getCity()).isEqualTo(stayDto.getGuestPersonalDetails().getCity());
        assertThat(updatedStay.getPostcode()).isEqualTo(stayDto.getGuestPersonalDetails().getPostcode());
        assertThat(updatedStay.getStreet()).isEqualTo(stayDto.getGuestPersonalDetails().getStreet());
        assertThat(updatedStay.getEmail()).isEqualTo(stayDto.getGuestPersonalDetails().getEmail());
        assertThat(updatedStay.getPhone()).isEqualTo(stayDto.getGuestPersonalDetails().getPhone().orElse(null));
        assertThat(updatedStay.getPassportId()).isEqualTo(stayDto.getGuestPersonalDetails().getPassportId().orElse(null));

        assertThat(updatedStay.getBoxNumber()).isEqualTo(stayDto.getStayDetails().getBoxNumber());
        assertThat(updatedStay.getCheckInDate()).isEqualTo(stayDto.getStayDetails().getCheckInDate());
        assertThat(updatedStay.getCheckOutDate()).isEqualTo(stayDto.getStayDetails().getCheckOutDate().orElse(null));
        assertThat(updatedStay.getArriveDate()).isEqualTo(stayDto.getStayDetails().getArriveDate());
        assertThat(updatedStay.getLeaveDate()).isEqualTo(stayDto.getStayDetails().getLeaveDate());
        assertThat(updatedStay.getHotel()).isEqualTo(stayDto.getStayDetails().getHotel());
        assertThat(updatedStay.getRoom()).isEqualTo(stayDto.getStayDetails().getRoom());
        assertThat(updatedStay.getLastDiveDate()).isEqualTo(stayDto.getStayDetails().getLastDiveDate());
        assertThat(updatedStay.getBrevet()).isEqualTo(stayDto.getStayDetails().getBrevet());
        assertThat(updatedStay.getDivesAmount()).isEqualTo(stayDto.getStayDetails().getDivesAmount());
        assertThat(updatedStay.isNitrox()).isEqualTo(stayDto.getStayDetails().isNitrox());
        assertThat(updatedStay.isMedicalStatement()).isEqualTo(stayDto.getStayDetails().isMedicalStatement());
        assertThat(updatedStay.isActive()).isEqualTo(stayDto.getStayDetails().isActive());
        assertThat(updatedStay.getPreBooking()).isEqualTo(stayDto.getStayDetails().getPreBooking());
    }
}

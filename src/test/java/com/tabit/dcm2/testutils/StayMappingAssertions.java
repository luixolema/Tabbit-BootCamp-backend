package com.tabit.dcm2.testutils;

import com.tabit.dcm2.entity.Loan;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.service.dto.CompleteStayDto;
import com.tabit.dcm2.service.dto.LoanDto;
import com.tabit.dcm2.service.dto.StayDto;

import java.util.Comparator;
import java.util.List;

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

    public static void assertCompleteStayDto(CompleteStayDto completeStayDto, Stay stay) {
        assertThat(completeStayDto.getGuestPersonalDetails().getId()).isEqualTo(stay.getGuest().getId());
        assertThat(completeStayDto.getGuestPersonalDetails().getFirstName()).isEqualTo(stay.getFirstName());
        assertThat(completeStayDto.getGuestPersonalDetails().getLastName()).isEqualTo(stay.getLastName());
        assertThat(completeStayDto.getGuestPersonalDetails().getBirthDate()).isEqualTo(stay.getBirthDate());
        assertThat(completeStayDto.getGuestPersonalDetails().getCity()).isEqualTo(stay.getCity());
        assertThat(completeStayDto.getGuestPersonalDetails().getCountry()).isEqualTo(stay.getCountry());
        assertThat(completeStayDto.getGuestPersonalDetails().getEmail()).isEqualTo(stay.getEmail());
        assertThat(completeStayDto.getGuestPersonalDetails().getNationality()).isEqualTo(stay.getNationality());
        assertThat(completeStayDto.getGuestPersonalDetails().getPassportId()).isEqualTo(stay.getPassportId());
        assertThat(completeStayDto.getGuestPersonalDetails().getPhone()).isEqualTo(stay.getPhone());
        assertThat(completeStayDto.getGuestPersonalDetails().getPostcode()).isEqualTo(stay.getPostcode());

        assertThat(completeStayDto.getStayDetails().getArriveDate()).isEqualTo(stay.getArriveDate());
        assertThat(completeStayDto.getStayDetails().getBoxNumber()).isEqualTo(stay.getBoxNumber());
        assertThat(completeStayDto.getStayDetails().getBrevet()).isEqualTo(stay.getBrevet());
        assertThat(completeStayDto.getStayDetails().getCheckInDate()).isEqualTo(stay.getCheckInDate());
        assertThat(completeStayDto.getStayDetails().getCheckOutDate()).isEqualTo(stay.getCheckOutDate());
        assertThat(completeStayDto.getStayDetails().getLastDiveDate()).isEqualTo(stay.getLastDiveDate());
        assertThat(completeStayDto.getStayDetails().getLeaveDate()).isEqualTo(stay.getLeaveDate());
        assertThat(completeStayDto.getStayDetails().getDivesAmount()).isEqualTo(stay.getDivesAmount());
        assertThat(completeStayDto.getStayDetails().getHotel()).isEqualTo(stay.getHotel());
        assertThat(completeStayDto.getStayDetails().getRoom()).isEqualTo(stay.getRoom());
        assertThat(completeStayDto.getStayDetails().isNitrox()).isEqualTo(stay.isNitrox());
        assertThat(completeStayDto.getStayDetails().isMedicalStatement()).isEqualTo(stay.isMedicalStatement());
        assertThat(completeStayDto.getStayDetails().getPreBooking()).isEqualTo(stay.getPreBooking());

        sortLoansDesc(stay.getLoans());
        assertLoansDtos(completeStayDto.getLoans(), stay.getLoans());
    }

    private static void sortLoansDesc(List<Loan> loans) {
        loans.sort(Comparator.comparing(Loan::getDateOut).reversed());
    }

    private static void assertLoansDtos(List<LoanDto> loanDtos, List<Loan> sortedLoans) {
        assertThat(loanDtos).hasSize(sortedLoans.size());
        for (int i = 0; i < loanDtos.size(); i++) {
            LoanDto loanDto = loanDtos.get(i);
            Loan loan = sortedLoans.get(i);

            LoanMappingAssertions.assertLoanDto(loanDto, loan);
        }
    }
}

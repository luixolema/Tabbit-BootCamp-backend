package com.tabit.dcm2.service.dto;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.Loan;
import com.tabit.dcm2.entity.Stay;

import java.util.ArrayList;
import java.util.List;

public class RandomStayDto {
    public static StayDto createRandomStayDto() {
        return createRandomStayDtoBuilder().build();
    }

    public static StayDto.Builder createRandomStayDtoBuilder() {
        return new StayDto.Builder()
                .withGuestPersonalDetails(RandomGuestPersonalDetailsDto.createRandomGuestPersonalDetailsDto())
                .withStayDetails(RandomStayDetailsDto.createRandomStayDetailsDto())
                .withLoanDetails(ImmutableList.of(RandomLoanDetailsDto.createRandomLoanDetailsDto()));
    }

    public static StayDto createStayDtoFromStay(Stay stay) {
        return createStayDtoBuilderFromStay(stay).build();
    }

    public static StayDto.Builder createStayDtoBuilderFromStay(Stay stay) {
        GuestPersonalDetailsDto guestPersonalDetails = new GuestPersonalDetailsDto.Builder()
                .withId(stay.getGuest().getId())
                .withFirstName(stay.getFirstName())
                .withLastName(stay.getLastName())
                .withBirthDate(stay.getBirthDate())
                .withNationality(stay.getNationality())
                .withCountry(stay.getCountry())
                .withCity(stay.getCity())
                .withPostcode(stay.getPostcode())
                .withStreet(stay.getStreet())
                .withEmail(stay.getEmail())
                .withPhone(stay.getPhone())
                .withPassportId(stay.getPassportId())
                .build();

        StayDetailsDto stayDetails = new StayDetailsDto.Builder()
                .withId(stay.getId())
                .withBoxNumber(stay.getBoxNumber())
                .withCheckInDate(stay.getCheckInDate())
                .withCheckOutDate(stay.getCheckOutDate())
                .withArriveDate(stay.getArriveDate())
                .withLeaveDate(stay.getLeaveDate())
                .withHotel(stay.getHotel())
                .withRoom(stay.getRoom())
                .withLastDiveDate(stay.getLastDiveDate())
                .withBrevet(stay.getBrevet())
                .withDivesAmount(stay.getDivesAmount())
                .withNitrox(stay.isNitrox())
                .withMedicalStatement(stay.isMedicalStatement())
                .withActive(stay.isActive())
                .withPreBooking(stay.getPreBooking())
                .build();

        List<LoanDetailsDto> loanDetailsDtos = new ArrayList<>();
        for (Loan loan : stay.getLoans()) {
            loanDetailsDtos.add(new LoanDetailsDto.Builder()
                    .withId(loan.getId())
                    .withType(loan.getEquipment().getEquipmentType().getType())
                    .withSerialNumber(loan.getEquipment().getSerialNumber())
                    .withDateOut(loan.getDateOut())
                    .withDateReturn(loan.getDateReturn())
                    .build()
            );
        }

        return new StayDto.Builder()
                .withGuestPersonalDetails(guestPersonalDetails)
                .withStayDetails(stayDetails)
                .withLoanDetails(loanDetailsDtos);
    }
}

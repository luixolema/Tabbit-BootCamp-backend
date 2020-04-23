package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.entity.Stay;

public class RandomStayDto {
    public static StayDto createRandomStayDto() {
        StayDto stayDto = new StayDto();
        stayDto.setGuestPersonalDetails(RandomGuestPersonalDetailsDto.createRandomGuestPersonalDetailsDto());
        stayDto.setStayDetails(RandomStayDetailsDto.createRandomStayDetailsDto());
        return stayDto;
    }

    public static StayDto createStayDtoFromStay(Stay stay) {
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

        StayDetailsDto stayDetails = new StayDetailsDto();
        stayDetails.setId(stay.getId());
        stayDetails.setBoxNumber(stay.getBoxNumber());
        stayDetails.setCheckInDate(stay.getCheckInDate());
        stayDetails.setCheckOutDate(stay.getCheckOutDate());
        stayDetails.setArriveDate(stay.getArriveDate());
        stayDetails.setLeaveDate(stay.getLeaveDate());
        stayDetails.setHotel(stay.getHotel());
        stayDetails.setRoom(stay.getRoom());
        stayDetails.setLastDiveDate(stay.getLastDiveDate());
        stayDetails.setBrevet(stay.getBrevet());
        stayDetails.setDivesAmount(stay.getDivesAmount());
        stayDetails.setNitrox(stay.isNitrox());
        stayDetails.setMedicalStatement(stay.isMedicalStatement());
        stayDetails.setPreBooking(stay.getPreBooking());

        StayDto stayDto = new StayDto();
        stayDto.setGuestPersonalDetails(guestPersonalDetails);
        stayDto.setStayDetails(stayDetails);

        stay.getLoans().forEach(stayDto::addLoanDetails);

        return stayDto;
    }
}

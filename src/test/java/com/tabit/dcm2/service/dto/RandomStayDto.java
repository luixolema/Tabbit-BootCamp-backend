package com.tabit.dcm2.service.dto;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.Loan;
import com.tabit.dcm2.entity.Stay;

public class RandomStayDto {
    public static StayDto createRandomStayDto() {
        StayDto stayDto = new StayDto();
        stayDto.setGuestPersonalDetails(RandomGuestPersonalDetailsDto.createRandomGuestPersonalDetailsDto());
        stayDto.setStayDetails(RandomStayDetailsDto.createRandomStayDetailsDto());
        return stayDto;
    }

    public static CompleteStayDto createRandomCompleteStayDto() {
        StayDto randomStayDto = RandomStayDto.createRandomStayDto();
        CompleteStayDto completeStayDto = new CompleteStayDto();

        completeStayDto.setStayDetails(randomStayDto.getStayDetails());
        completeStayDto.setGuestPersonalDetails(randomStayDto.getGuestPersonalDetails());
        completeStayDto.setLoanDtos((ImmutableList.of(RandomLoanDto.createRandomLoanDto())));

        return completeStayDto;
    }

    public static StayDto createStayDtoFromStay(Stay stay) {
        GuestPersonalDetailsDto guestPersonalDetails = new GuestPersonalDetailsDto();
        guestPersonalDetails.setId(stay.getGuest().getId());
        guestPersonalDetails.setFirstName(stay.getFirstName());
        guestPersonalDetails.setLastName(stay.getLastName());
        guestPersonalDetails.setBirthDate(stay.getBirthDate());
        guestPersonalDetails.setNationality(stay.getNationality());
        guestPersonalDetails.setCountry(stay.getCountry());
        guestPersonalDetails.setCity(stay.getCity());
        guestPersonalDetails.setPostcode(stay.getPostcode());
        guestPersonalDetails.setStreet(stay.getStreet());
        guestPersonalDetails.setEmail(stay.getEmail());
        guestPersonalDetails.setPhone(stay.getPhone());
        guestPersonalDetails.setPassportId(stay.getPassportId());

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

        return stayDto;
    }

    public static LoanDto mapLoanToLoanDto(Loan loan) {
        LoanDto loanDto = new LoanDto();
        loanDto.setId(loan.getId());
        loanDto.setDateOut(loan.getDateOut());
        loanDto.setDateReturn(loan.getDateReturn());
        loanDto.setSerial(loan.getEquipment().getSerialNumber());
        loanDto.setType(loan.getEquipment().getEquipmentType().getType());

        return loanDto;
    }
}

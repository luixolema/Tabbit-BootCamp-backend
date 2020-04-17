package com.tabit.dcm2.controller.util;

import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.Loan;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.service.dto.*;

import java.util.List;
import java.util.stream.Collectors;

public class MapperUtil {

    public static StayDto mapStayToStayDto(Stay stay) {
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
        stayDetails.setActive(stay.isActive());
        stayDetails.setPreBooking(stay.getPreBooking());

        StayDto stayDto = new StayDto();
        stayDto.setGuestPersonalDetails(guestPersonalDetails);
        stayDto.setStayDetails(stayDetails);

        return stayDto;
    }

    public static CompleteStayDto mapStayToCompleteStayDto(Stay stay) {
        CompleteStayDto completeStayDto = new CompleteStayDto();

        List<LoanDto> loanDtos = stay.getLoans().stream().map(MapperUtil::mapLoanToLoanDto).collect(Collectors.toList());


        StayDto stayDto = MapperUtil.mapStayToStayDto(stay);
        completeStayDto.setGuestPersonalDetails(stayDto.getGuestPersonalDetails());
        completeStayDto.setStayDetails(stayDto.getStayDetails());
        completeStayDto.setLoanDtos(loanDtos);

        return completeStayDto;
    }

    public static StayDto mapGuestToStayDto(Guest guest) {
        GuestPersonalDetailsDto guestPersonalDetails = new GuestPersonalDetailsDto();
        guestPersonalDetails.setId(guest.getId());
        guestPersonalDetails.setFirstName(guest.getFirstName());
        guestPersonalDetails.setLastName(guest.getLastName());
        guestPersonalDetails.setBirthDate(guest.getBirthDate());
        guestPersonalDetails.setNationality(guest.getNationality());
        guestPersonalDetails.setCountry(guest.getCountry());
        guestPersonalDetails.setCity(guest.getCity());
        guestPersonalDetails.setPostcode(guest.getPostcode());
        guestPersonalDetails.setStreet(guest.getStreet());
        guestPersonalDetails.setEmail(guest.getEmail());
        guestPersonalDetails.setPhone(guest.getPhone());
        guestPersonalDetails.setPassportId(guest.getPassportId());

        StayDto stayDto = new StayDto();
        stayDto.setGuestPersonalDetails(guestPersonalDetails);

        return stayDto;
    }

    public static GuestDto mapGuestToGuestDTO(Guest guest) {
        GuestDto guestDto = new GuestDto();
        guestDto.setId(guest.getId());
        if (guest.isCheckedin()) {
            guestDto.setBoxNumber(guest.getStays().get(0).getBoxNumber());
        }
        guestDto.setFirstName(guest.getFirstName());
        guestDto.setLastName(guest.getLastName());
        guestDto.setCheckedin(guest.isCheckedin());
        return guestDto;
    }

    public static Stay mapStayDtoToStayWithoutGuestRef(StayDto stayDto) {
        Stay stay = new Stay();
        stay.setFirstName(stayDto.getGuestPersonalDetails().getFirstName());
        stay.setLastName(stayDto.getGuestPersonalDetails().getLastName());
        stay.setBirthDate(stayDto.getGuestPersonalDetails().getBirthDate());
        stay.setNationality(stayDto.getGuestPersonalDetails().getNationality());
        stay.setCountry(stayDto.getGuestPersonalDetails().getCountry());
        stay.setCity(stayDto.getGuestPersonalDetails().getCity());
        stay.setPostcode(stayDto.getGuestPersonalDetails().getPostcode());
        stay.setStreet(stayDto.getGuestPersonalDetails().getStreet());
        stay.setEmail(stayDto.getGuestPersonalDetails().getEmail());
        stay.setPhone(stayDto.getGuestPersonalDetails().getPhone());
        stay.setPassportId(stayDto.getGuestPersonalDetails().getPassportId());
        stay.setBoxNumber(stayDto.getStayDetails().getBoxNumber());
        stay.setCheckInDate(stayDto.getStayDetails().getCheckInDate());
        stay.setCheckOutDate(stayDto.getStayDetails().getCheckOutDate());
        stay.setArriveDate(stayDto.getStayDetails().getArriveDate());
        stay.setLeaveDate(stayDto.getStayDetails().getLeaveDate());
        stay.setHotel(stayDto.getStayDetails().getHotel());
        stay.setRoom(stayDto.getStayDetails().getRoom());
        stay.setLastDiveDate(stayDto.getStayDetails().getLastDiveDate());
        stay.setBrevet(stayDto.getStayDetails().getBrevet());
        stay.setDivesAmount(stayDto.getStayDetails().getDivesAmount());
        stay.setNitrox(stayDto.getStayDetails().isNitrox());
        stay.setMedicalStatement(stayDto.getStayDetails().isMedicalStatement());
        stay.setActive(stayDto.getStayDetails().isActive());
        stay.setPreBooking(stayDto.getStayDetails().getPreBooking());

        return stay;
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

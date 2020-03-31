package com.tabit.dcm2.controller.util;

import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.service.dto.StayDto;

public class MapperUtil {

    public static StayDto mapStayToStayDto(Stay stay) {
        StayDto stayDto = new StayDto();
        stayDto.setId(stay.getId());

        StayDto.GuestPersonalDetails guestPersonalDetails = stayDto.getGuestPersonalDetails();
        guestPersonalDetails.setGuestId(stay.getGuest().getId());
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

        StayDto.StayDetails stayDetails = stayDto.getStayDetails();
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

        return stayDto;
    }

    public static StayDto mapGuestToStayDto(Guest guest) {
        StayDto stayDto = new StayDto();

        StayDto.GuestPersonalDetails guestPersonalDetails = stayDto.getGuestPersonalDetails();
        guestPersonalDetails.setGuestId(guest.getId());
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

        return stayDto;
    }
}

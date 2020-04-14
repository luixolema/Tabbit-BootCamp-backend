package com.tabit.dcm2.service.util;

import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.service.dto.GuestPersonalDetailsDto;
import org.springframework.stereotype.Component;

@Component
public class GuestMapper {
    public void mapPersonalDetailsFromDto(Guest guestInDb, GuestPersonalDetailsDto guestPersonalDetailsDto) {
        guestInDb.setFirstName(guestPersonalDetailsDto.getFirstName());
        guestInDb.setLastName(guestPersonalDetailsDto.getLastName());
        guestInDb.setBirthDate(guestPersonalDetailsDto.getBirthDate());
        guestInDb.setEmail(guestPersonalDetailsDto.getEmail());
        guestInDb.setNationality(guestPersonalDetailsDto.getNationality());
        guestInDb.setCity(guestPersonalDetailsDto.getCity());
        guestInDb.setPassportId(guestPersonalDetailsDto.getPassportId());
        guestInDb.setPhone(guestPersonalDetailsDto.getPhone());
        guestInDb.setCountry(guestPersonalDetailsDto.getCountry());
        guestInDb.setPostcode(guestPersonalDetailsDto.getPostcode());
        guestInDb.setStreet(guestPersonalDetailsDto.getStreet());
    }

    public GuestPersonalDetailsDto guestPersonalDetailsDtoFromGuest(Guest guest) {
        GuestPersonalDetailsDto guestPersonalDetailsDto = new GuestPersonalDetailsDto();

        guestPersonalDetailsDto.setId(guest.getId());
        guestPersonalDetailsDto.setFirstName(guest.getFirstName());
        guestPersonalDetailsDto.setLastName(guest.getLastName());
        guestPersonalDetailsDto.setBirthDate(guest.getBirthDate());
        guestPersonalDetailsDto.setEmail(guest.getEmail());
        guestPersonalDetailsDto.setNationality(guest.getNationality());
        guestPersonalDetailsDto.setCity(guest.getCity());
        guestPersonalDetailsDto.setPassportId(guest.getPassportId());
        guestPersonalDetailsDto.setPhone(guest.getPhone());
        guestPersonalDetailsDto.setCountry(guest.getCountry());
        guestPersonalDetailsDto.setPostcode(guest.getPostcode());
        guestPersonalDetailsDto.setStreet(guest.getStreet());

        return guestPersonalDetailsDto;
    }
}

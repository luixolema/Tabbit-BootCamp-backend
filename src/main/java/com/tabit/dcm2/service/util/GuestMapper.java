package com.tabit.dcm2.service.util;

import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.service.dto.AbstractGuestPersonalDetailsDto;
import org.springframework.stereotype.Component;

@Component
public class GuestMapper {
    public void mapPersonalDetailsFromDto(Guest guestInDb, AbstractGuestPersonalDetailsDto guestPersonalDetailsDto) {
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
}

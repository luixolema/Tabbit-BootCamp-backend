package com.tabit.dcm2.service.impl;

import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.exception.ResourceNotFoundException;
import com.tabit.dcm2.repository.IGuestRepo;
import com.tabit.dcm2.service.GuestFilterType;
import com.tabit.dcm2.service.IGuestService;
import com.tabit.dcm2.service.dto.GuestPersonalDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService implements IGuestService {
    @Autowired
    private IGuestRepo guestRepo;

    @Autowired
    private StayService stayService;

    @Override
    public List<Guest> getAllGuests(GuestFilterType guestFilterType) {
        return guestFilterType == GuestFilterType.ALL ? guestRepo.findAll() : guestRepo.findByCheckedin(guestFilterType == GuestFilterType.CHECKED_IN);
    }

    @Override
    public Guest getGuestById(Long guestId) {
        return guestRepo.findById(guestId).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void updatePersonalDetails(GuestPersonalDetailsDto guestPersonalDetailsDto) {
        Guest guest = getGuestById(guestPersonalDetailsDto.getId());
        guestPersonalDetailsDtoToGuestMapping(guestPersonalDetailsDto, guest);
        guestRepo.save(guest);
    }

    private Guest guestPersonalDetailsDtoToGuestMapping(GuestPersonalDetailsDto guestPersonalDetailsDto, Guest guestInDb) {
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

        return guestInDb;
    }
}

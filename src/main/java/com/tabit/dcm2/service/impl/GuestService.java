package com.tabit.dcm2.service.impl;

import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.mapper.IMapperFactory;
import com.tabit.dcm2.repository.IGuestRepo;
import com.tabit.dcm2.service.GuestDto;
import com.tabit.dcm2.service.GuestsDto;
import com.tabit.dcm2.service.IGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService implements IGuestService {
    @Autowired
    private IGuestRepo guestRepo;

    @Autowired
    private IMapperFactory mapperFactory;

    @Override
    public GuestsDto getGuests(Boolean checkedIn) {
        GuestsDto guestsDto = new GuestsDto();
        List<Guest> guests = checkedIn == null ? guestRepo.findAll() : guestRepo.findByCheckedin(checkedIn);
        try {
            guestsDto.setGuests(mapperFactory.getMapper(Guest.class, GuestDto.class).map(guests));
            guestsDto.setTotal(guests.size());
        } catch (Exception e) {
            System.out.println("Failed to obtain guests");
        }
        return guestsDto;
    }
}

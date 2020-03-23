package com.tabit.dcm2.service.impl;

import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.GuestDto;
import com.tabit.dcm2.mapper.IMapperFactory;
import com.tabit.dcm2.repository.IGuestRepo;
import com.tabit.dcm2.service.IGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GuestService implements IGuestService {
    @Autowired
    private IGuestRepo guestRepo;

    @Autowired
    private IMapperFactory mapperFactory;

    @Override
    public List<GuestDto> getGuests(Boolean checkedIn) {

        List<Guest> guests = checkedIn == null ? guestRepo.findAll() : guestRepo.findByCheckedin(checkedIn);
        try {
            return mapperFactory.getMapper(Guest.class,GuestDto.class).map(guests);
        } catch (Exception e) {
            System.out.println("Failed to obtain guests");
            return Collections.EMPTY_LIST;
        }
    }
}

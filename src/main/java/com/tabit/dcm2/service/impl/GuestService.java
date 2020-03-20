package com.tabit.dcm2.service.impl;

import com.tabit.dcm2.dao.IGuestRepo;
import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.GuestDto;
import com.tabit.dcm2.service.IGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GuestService implements IGuestService {
    @Autowired
    private IGuestRepo  guestRepo;

    @Override
    public List<GuestDto> getGuests() {
        List<Guest> guests = guestRepo.getGuests();

        return Collections.emptyList();
    }

    @Override
    public GuestDto getGuest(Long guestId) {
        return null;
    }

}

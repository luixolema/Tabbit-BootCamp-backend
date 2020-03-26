package com.tabit.dcm2.service.impl;

import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.repository.IGuestRepo;
import com.tabit.dcm2.service.GuestFilterType;
import com.tabit.dcm2.service.IGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService implements IGuestService {
    @Autowired
    private IGuestRepo guestRepo;

    @Override
    public List<Guest> getGuests(GuestFilterType guestFilterType) {
        return guestFilterType == GuestFilterType.ALL ? guestRepo.findAll() : guestRepo.findByCheckedin(guestFilterType == GuestFilterType.CHECKED_IN);
    }
}

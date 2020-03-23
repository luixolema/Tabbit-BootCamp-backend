package com.tabit.dcm2.service.impl;

import com.tabit.dcm2.entity.GuestDto;
import com.tabit.dcm2.service.IGuestService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GuestService implements IGuestService {
    @Override
    public List<GuestDto> getGuests() {
        return Collections.emptyList();
    }

    @Override
    public GuestDto getGuest(Long guestId) {
        return null;
    }

    @Override
    public void addGuest(GuestDto guest) {

    }

    @Override
    public void checkIn(GuestDto guest) {

    }
}

package com.tabit.dcm2.service;

import com.tabit.dcm2.entity.GuestDto;

import java.util.List;

public interface IGuestService {
    List<GuestDto> getGuests();

    GuestDto getGuest(Long guestId);
}

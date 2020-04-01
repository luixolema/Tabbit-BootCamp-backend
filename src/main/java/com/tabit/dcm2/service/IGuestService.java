package com.tabit.dcm2.service;

import com.tabit.dcm2.entity.Guest;

import java.util.List;

public interface IGuestService {
    List<Guest> getAllGuests(GuestFilterType guestFilterType);
    Guest getGuestById(Long guestId);
}

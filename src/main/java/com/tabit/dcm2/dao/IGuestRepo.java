package com.tabit.dcm2.dao;

import com.tabit.dcm2.entity.Guest;

import java.util.List;

public interface IGuestRepo {
    Guest getGuest(Long guestId);

    List<Guest> getGuests();
}

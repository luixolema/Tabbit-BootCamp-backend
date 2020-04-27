package com.tabit.dcm2.service;

import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.service.dto.CheckInDto;
import com.tabit.dcm2.service.dto.GuestCreationDto;
import com.tabit.dcm2.service.dto.GuestPersonalDetailsDto;

import java.util.List;

public interface IGuestService {
    List<Guest> getAllGuests(GuestFilterType guestFilterType);

    Guest getGuestById(Long guestId);

    void updatePersonalDetails(GuestPersonalDetailsDto personalDetailsDto);

    void checkIn(CheckInDto checkInDto);

    Guest create(GuestCreationDto guest);
}


package com.tabit.dcm2.service;

import com.tabit.dcm2.controller.requests.StayRequest;
import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.service.dto.GuestPersonalDetailsDto;

import java.util.List;

public interface IGuestService {
    List<Guest> getAllGuests(GuestFilterType guestFilterType);

    Guest getGuestById(Long guestId);

    void updatePersonalDetails(GuestPersonalDetailsDto personalDetailsDto);

    Stay getCurrentStay(Guest guest);

    Stay addStay(Guest guest, StayRequest stayRequest);
}

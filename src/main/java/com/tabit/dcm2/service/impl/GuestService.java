package com.tabit.dcm2.service.impl;

import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.repository.IGuestRepo;
import com.tabit.dcm2.service.GuestDto;
import com.tabit.dcm2.service.GuestFilterType;
import com.tabit.dcm2.service.GuestsDto;
import com.tabit.dcm2.service.IGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class GuestService implements IGuestService {
    private static final Function<Guest, GuestDto> GUEST_TO_GUEST_DTO = guest -> {
        GuestDto guestDto = new GuestDto();
        guestDto.setId(guest.getId());
        if (guest.getBox() != null) {
            guestDto.setBoxId(guest.getBox().getId());
        }
        guestDto.setFirstName(guest.getFirstName());
        guestDto.setLastName(guest.getLastName());
        guestDto.setCheckedin(guest.isCheckedin());
        return guestDto;
    };

    @Autowired
    private IGuestRepo guestRepo;

    @Override
    public GuestsDto getGuests(GuestFilterType guestFilterType) {
        List<Guest> guests = guestFilterType == GuestFilterType.ALL ? guestRepo.findAll() : guestRepo.findByCheckedin(guestFilterType == GuestFilterType.CHECKED_IN);
        List<GuestDto> guestDtos = guests.stream().map(GUEST_TO_GUEST_DTO).collect(Collectors.toList());

        return new GuestsDto(guestDtos);
    }
}

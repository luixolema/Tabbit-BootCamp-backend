package com.tabit.dcm2.service.impl;

import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.exception.ResourceNotFoundException;
import com.tabit.dcm2.repository.IGuestRepo;
import com.tabit.dcm2.service.GuestFilterType;
import com.tabit.dcm2.service.IGuestService;
import com.tabit.dcm2.service.dto.GuestPersonalDetailsDto;
import com.tabit.dcm2.service.util.GuestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService implements IGuestService {
    @Autowired
    private IGuestRepo guestRepo;
    @Autowired
    private GuestMapper guestMapper;

    @Override
    public List<Guest> getAllGuests(GuestFilterType guestFilterType) {
        return guestFilterType == GuestFilterType.ALL ? guestRepo.findAll() : guestRepo.findByCheckedin(guestFilterType == GuestFilterType.CHECKED_IN);
    }

    @Override
    public Guest getGuestById(Long guestId) {
        return guestRepo.findById(guestId).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void updatePersonalDetails(GuestPersonalDetailsDto guestPersonalDetailsDto) {
        Guest guest = getGuestById(guestPersonalDetailsDto.getId());
        guestMapper.mapPersonalDetailsFromDto(guest, guestPersonalDetailsDto);
        guestRepo.save(guest);
    }
}

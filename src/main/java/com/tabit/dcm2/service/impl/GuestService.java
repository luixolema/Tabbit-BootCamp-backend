package com.tabit.dcm2.service.impl;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.controller.requests.StayRequest;
import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.exception.ResourceNotFoundException;
import com.tabit.dcm2.repository.IGuestRepo;
import com.tabit.dcm2.repository.IStayRepo;
import com.tabit.dcm2.service.GuestFilterType;
import com.tabit.dcm2.service.IGuestService;
import com.tabit.dcm2.service.dto.GuestPersonalDetailsDto;
import com.tabit.dcm2.service.util.GuestMapper;
import com.tabit.dcm2.service.util.StayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuestService implements IGuestService {
    @Autowired
    private IGuestRepo guestRepo;
    @Autowired
    private GuestMapper guestMapper;
    @Autowired
    private StayMapper stayMapper;

    @Autowired
    private StayService stayService;
    @Autowired
    private IStayRepo stayRepo;


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

        if (guest.isCheckedin()) {
            //then we should also update the current stay personal details
            Stay currentStay = getCurrentStay(guest);
            stayService.updatePersonalDetails(currentStay, guestPersonalDetailsDto);
        }
    }

    @Override
    public Stay getCurrentStay(Guest guest) {
        Optional<Stay> activeStay = guest.getStays().stream().findFirst();
        return activeStay.orElseThrow(() -> new ResourceNotFoundException("It was not possible to find any stay for the guest"));
    }

    @Override
    public Stay addStay(Guest guest, StayRequest stayRequest) {
        Stay stay = stayMapper.toStay(stayRequest);
        stay.setActive(true);
        stay.setGuest(guest);
        guest.addStays(ImmutableList.of(stay));
        stayRepo.save(stay);

        return stay;
    }


}

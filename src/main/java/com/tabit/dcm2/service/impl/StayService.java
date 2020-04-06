package com.tabit.dcm2.service.impl;

import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.exception.ResourceNotFoundException;
import com.tabit.dcm2.repository.IGuestRepo;
import com.tabit.dcm2.repository.IStayRepo;
import com.tabit.dcm2.service.IStayService;
import com.tabit.dcm2.service.dto.StayDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StayService implements IStayService {
    @Autowired
    private IStayRepo stayRepo;

    @Autowired
    private IGuestRepo guestRepo;

    @Override
    public Stay findById(Long stayId) {
        Optional<Stay> stay = stayRepo.findById(stayId);
        return stay.orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void updateStay(StayDto stayDto) {
        Stay stay = findById(stayDto.getStayDetails().getId());
        stayRepo.save(setStay(stay, stayDto));

        Guest guest = guestRepo.findById(stayDto.getGuestPersonalDetails().getId()).get();
        if (isPersonalDetailsChanged(guest, stayDto)) {
            guestRepo.save(setGuest(guest, stayDto));
        }
    }

    private Stay setStay(Stay stay, StayDto stayDto) {
        stay.setFirstName(stayDto.getGuestPersonalDetails().getFirstName());
        stay.setLastName(stayDto.getGuestPersonalDetails().getLastName());
        stay.setBoxNumber(stayDto.getStayDetails().getBoxNumber());
        stay.setBirthDate(stayDto.getGuestPersonalDetails().getBirthDate());
        stay.setNationality(stayDto.getGuestPersonalDetails().getNationality());
        stay.setCountry(stayDto.getGuestPersonalDetails().getCountry());
        stay.setCity(stayDto.getGuestPersonalDetails().getCity());
        stay.setPostcode(stayDto.getGuestPersonalDetails().getPostcode());
        stay.setStreet(stayDto.getGuestPersonalDetails().getStreet());
        stay.setEmail(stayDto.getGuestPersonalDetails().getEmail());
        stay.setPhone(stayDto.getGuestPersonalDetails().getPhone());
        stay.setPassportId(stayDto.getGuestPersonalDetails().getPassportId());
        stay.setCheckInDate(stayDto.getStayDetails().getCheckInDate());
        stay.setCheckOutDate(stayDto.getStayDetails().getCheckOutDate());
        stay.setArriveDate(stayDto.getStayDetails().getArriveDate());
        stay.setLeaveDate(stayDto.getStayDetails().getLeaveDate());
        stay.setHotel(stayDto.getStayDetails().getHotel());
        stay.setRoom(stayDto.getStayDetails().getRoom());
        stay.setLastDiveDate(stayDto.getStayDetails().getLastDiveDate());
        stay.setBrevet(stayDto.getStayDetails().getBrevet());
        stay.setDivesAmount(stayDto.getStayDetails().getDivesAmount());
        stay.setNitrox(stayDto.getStayDetails().isNitrox());
        stay.setActive(stayDto.getStayDetails().isActive());
        stay.setPreBoocking(stayDto.getStayDetails().getPreBoocking());

        return stay;
    }

    private boolean isPersonalDetailsChanged(Guest guest, StayDto stayDto) {
        return !guest.getFirstName().equals(stayDto.getGuestPersonalDetails().getFirstName()) ||
                !guest.getLastName().equals(stayDto.getGuestPersonalDetails().getLastName()) ||
                !guest.getBirthDate().equals(stayDto.getGuestPersonalDetails().getBirthDate()) ||
                !guest.getNationality().equals(stayDto.getGuestPersonalDetails().getNationality()) ||
                !guest.getCountry().equals(stayDto.getGuestPersonalDetails().getCountry()) ||
                !guest.getCity().equals(stayDto.getGuestPersonalDetails().getCity()) ||
                !guest.getPostcode().equals(stayDto.getGuestPersonalDetails().getPostcode()) ||
                !guest.getStreet().equals(stayDto.getGuestPersonalDetails().getStreet()) ||
                !guest.getEmail().equals(stayDto.getGuestPersonalDetails().getEmail()) ||
                !guest.getPhone().equals(stayDto.getGuestPersonalDetails().getPhone()) ||
                !guest.getPassportId().equals(stayDto.getGuestPersonalDetails().getPassportId());
    }

    private Guest setGuest(Guest guest, StayDto stayDto) {
        guest.setFirstName(stayDto.getGuestPersonalDetails().getFirstName());
        guest.setLastName(stayDto.getGuestPersonalDetails().getLastName());
        guest.setBirthDate(stayDto.getGuestPersonalDetails().getBirthDate());
        guest.setNationality(stayDto.getGuestPersonalDetails().getNationality());
        guest.setCountry(stayDto.getGuestPersonalDetails().getCountry());
        guest.setCity(stayDto.getGuestPersonalDetails().getCity());
        guest.setPostcode(stayDto.getGuestPersonalDetails().getPostcode());
        guest.setStreet(stayDto.getGuestPersonalDetails().getStreet());
        guest.setEmail(stayDto.getGuestPersonalDetails().getEmail());
        guest.setPhone(stayDto.getGuestPersonalDetails().getPhone());
        guest.setPassportId(stayDto.getGuestPersonalDetails().getPassportId());

        return guest;
    }
}
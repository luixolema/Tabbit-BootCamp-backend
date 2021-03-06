package com.tabit.dcm2.service.impl;

import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.exception.ResourceNotFoundException;
import com.tabit.dcm2.repository.IGuestRepo;
import com.tabit.dcm2.repository.IStayRepo;
import com.tabit.dcm2.service.IBoxManagementService;
import com.tabit.dcm2.service.IStayService;
import com.tabit.dcm2.service.dto.StayDto;
import com.tabit.dcm2.service.util.GuestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StayService implements IStayService {
    @Autowired
    private IStayRepo stayRepo;
    @Autowired
    private IGuestRepo guestRepo;
    @Autowired
    private GuestMapper guestMapper;
    @Autowired
    private IBoxManagementService boxManagementService;

    @Override
    public Stay findById(Long stayId) {
        Optional<Stay> stay = stayRepo.findById(stayId);
        return stay.orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void updateStay(StayDto stayDto) {

        Stay stay = findById(stayDto.getStayDetails().getId());

        if (!stayDto.getStayDetails().getBoxNumber().equals(stay.getBoxNumber())) { //if box updated
            boxManagementService.reserveBox(stayDto.getStayDetails().getBoxNumber());
            boxManagementService.releaseBox(stay.getBoxNumber());
        }

        stayRepo.save(mapStayFromStayDto(stay, stayDto));

        Guest guest = stay.getGuest();
        if (isPersonalDetailsChanged(guest, stayDto)) {
            guestMapper.mapPersonalDetailsFromDto(guest, stayDto.getGuestPersonalDetails());
            guestRepo.save(guest);
        }
    }

    private Stay mapStayFromStayDto(Stay stay, StayDto stayDto) {
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
        stay.setPhone(stayDto.getGuestPersonalDetails().getPhone().get());
        stay.setPassportId(stayDto.getGuestPersonalDetails().getPassportId().get());
        stay.setCheckInDate(stayDto.getStayDetails().getCheckInDate());
        stay.setCheckOutDate(stayDto.getStayDetails().getCheckOutDate().orElse(null));
        stay.setArriveDate(stayDto.getStayDetails().getArriveDate());
        stay.setLeaveDate(stayDto.getStayDetails().getLeaveDate());
        stay.setHotel(stayDto.getStayDetails().getHotel());
        stay.setRoom(stayDto.getStayDetails().getRoom());
        stay.setLastDiveDate(stayDto.getStayDetails().getLastDiveDate());
        stay.setBrevet(stayDto.getStayDetails().getBrevet());
        stay.setDivesAmount(stayDto.getStayDetails().getDivesAmount());
        stay.setNitrox(stayDto.getStayDetails().isNitrox());
        stay.setActive(stayDto.getStayDetails().isActive());
        stay.setPreBooking(stayDto.getStayDetails().getPreBooking());
        stay.setMedicalStatement(stayDto.getStayDetails().isMedicalStatement());

        return stay;
    }

    @SuppressWarnings({"checkstyle:cyclomaticcomplexity", "checkstyle:booleanexpressioncomplexity"})
    private boolean isPersonalDetailsChanged(Guest guest, StayDto stayDto) {
        return !guest.getFirstName().equals(stayDto.getGuestPersonalDetails().getFirstName())
                || !guest.getLastName().equals(stayDto.getGuestPersonalDetails().getLastName())
                || !guest.getBirthDate().equals(stayDto.getGuestPersonalDetails().getBirthDate())
                || !guest.getNationality().equals(stayDto.getGuestPersonalDetails().getNationality())
                || !guest.getCountry().equals(stayDto.getGuestPersonalDetails().getCountry())
                || !guest.getCity().equals(stayDto.getGuestPersonalDetails().getCity())
                || !guest.getPostcode().equals(stayDto.getGuestPersonalDetails().getPostcode())
                || !guest.getStreet().equals(stayDto.getGuestPersonalDetails().getStreet())
                || !guest.getEmail().equals(stayDto.getGuestPersonalDetails().getEmail())
                || !guest.getPhone().equals(stayDto.getGuestPersonalDetails().getPhone().orElse(null))
                || !guest.getPassportId().equals(stayDto.getGuestPersonalDetails().getPassportId().orElse(null));
    }
}
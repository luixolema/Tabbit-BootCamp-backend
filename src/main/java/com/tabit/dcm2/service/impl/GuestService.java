package com.tabit.dcm2.service.impl;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.exception.GuestIllegalStateException;
import com.tabit.dcm2.exception.ResourceNotFoundException;
import com.tabit.dcm2.repository.IGuestRepo;
import com.tabit.dcm2.repository.IStayRepo;
import com.tabit.dcm2.service.GuestFilterType;
import com.tabit.dcm2.service.IBoxManagementService;
import com.tabit.dcm2.service.IGuestService;
import com.tabit.dcm2.service.dto.CheckInDto;
import com.tabit.dcm2.service.dto.GuestCreationDto;
import com.tabit.dcm2.service.dto.GuestPersonalDetailsDto;
import com.tabit.dcm2.service.util.GuestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class GuestService implements IGuestService {
    private static final Function<CheckInDto, Stay> MAP_CHECKIN_DTO_TO_STAY = checkInDto -> {
        Stay stay = new Stay();
        stay.setFirstName(checkInDto.getGuestPersonalDetails().getFirstName());
        stay.setLastName(checkInDto.getGuestPersonalDetails().getLastName());
        stay.setBirthDate(checkInDto.getGuestPersonalDetails().getBirthDate());
        stay.setNationality(checkInDto.getGuestPersonalDetails().getNationality());
        stay.setCountry(checkInDto.getGuestPersonalDetails().getCountry());
        stay.setCity(checkInDto.getGuestPersonalDetails().getCity());
        stay.setPostcode(checkInDto.getGuestPersonalDetails().getPostcode());
        stay.setStreet(checkInDto.getGuestPersonalDetails().getStreet());
        stay.setEmail(checkInDto.getGuestPersonalDetails().getEmail());
        stay.setPhone(checkInDto.getGuestPersonalDetails().getPhone().get());
        stay.setPassportId(checkInDto.getGuestPersonalDetails().getPassportId().get());
        stay.setBoxNumber(checkInDto.getStayDetails().getBoxNumber());
        stay.setCheckInDate(checkInDto.getStayDetails().getCheckInDate());
        stay.setArriveDate(checkInDto.getStayDetails().getArriveDate());
        stay.setLeaveDate(checkInDto.getStayDetails().getLeaveDate());
        stay.setHotel(checkInDto.getStayDetails().getHotel());
        stay.setRoom(checkInDto.getStayDetails().getRoom());
        stay.setLastDiveDate(checkInDto.getStayDetails().getLastDiveDate());
        stay.setBrevet(checkInDto.getStayDetails().getBrevet());
        stay.setDivesAmount(checkInDto.getStayDetails().getDivesAmount());
        stay.setNitrox(checkInDto.getStayDetails().isNitrox());
        stay.setMedicalStatement(checkInDto.getStayDetails().isMedicalStatement());
        stay.setActive(true);
        stay.setPreBooking(checkInDto.getStayDetails().getPreBooking());

        return stay;
    };

    @Autowired
    private IGuestRepo guestRepo;
    @Autowired
    private GuestMapper guestMapper;
    @Autowired
    private IBoxManagementService boxManagementService;
    @Autowired
    private IStayRepo stayRepo;
    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public List<Guest> getAllGuests(GuestFilterType guestFilterType) {
        Long diveCenterId = authenticationService.getLoggedInUser().getDiveCenter().getId();
        return guestFilterType == GuestFilterType.ALL ? guestRepo.findByDiveCenterId(diveCenterId) : guestRepo.findByCheckedinAndDiveCenterId(guestFilterType == GuestFilterType.CHECKED_IN, diveCenterId);
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

    @Override
    public void checkIn(CheckInDto checkInDto) {
        Guest guest = getGuestById(checkInDto.getGuestPersonalDetails().getId());

        if (guest.isCheckedin()) {
            throw new GuestIllegalStateException();
        }

        boxManagementService.reserveBox(checkInDto.getStayDetails().getBoxNumber());

        guestMapper.mapPersonalDetailsFromDto(guest, checkInDto.getGuestPersonalDetails());
        Stay newStay = MAP_CHECKIN_DTO_TO_STAY.apply(checkInDto);
        newStay.setDiveCenter(authenticationService.getLoggedInUser().getDiveCenter());
        guest.addStays(ImmutableList.of(newStay));
        guest.setCheckedin(true);

        stayRepo.save(newStay);
        guestRepo.save(guest);
    }

    @Override
    public Guest create(GuestCreationDto guestCreationDto) {
        Guest guest = new Guest();
        guestMapper.mapPersonalDetailsFromDto(guest, guestCreationDto);
        guest.setDiveCenter(authenticationService.getLoggedInUser().getDiveCenter());
        return guestRepo.save(guest);
    }
}

package com.tabit.dcm2.controller;

import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.service.GuestFilterType;
import com.tabit.dcm2.service.IGuestService;
import com.tabit.dcm2.service.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.tabit.dcm2.controller.StayController.MAP_STAY_TO_STAY_DTO;

@RestController
@CrossOrigin
@RequestMapping("/api/guests")
public class GuestController {

    private static Function<Guest, GuestDto> MAP_GUEST_TO_GUEST_DTO = guest -> {
        GuestDto guestDto = new GuestDto();
        guestDto.setId(guest.getId());
        if (guest.isCheckedin()) {
            guestDto.setBoxNumber(guest.getStays().get(0).getBoxNumber());
        }
        guestDto.setFirstName(guest.getFirstName());
        guestDto.setLastName(guest.getLastName());
        guestDto.setCheckedin(guest.isCheckedin());
        return guestDto;
    };

    private static Function<Guest, StayDto> MAP_GUEST_TO_STAY_DTO = guest -> {
        GuestPersonalDetailsDto guestPersonalDetails = new GuestPersonalDetailsDto();
        guestPersonalDetails.setId(guest.getId());
        guestPersonalDetails.setFirstName(guest.getFirstName());
        guestPersonalDetails.setLastName(guest.getLastName());
        guestPersonalDetails.setBirthDate(guest.getBirthDate());
        guestPersonalDetails.setNationality(guest.getNationality());
        guestPersonalDetails.setCountry(guest.getCountry());
        guestPersonalDetails.setCity(guest.getCity());
        guestPersonalDetails.setPostcode(guest.getPostcode());
        guestPersonalDetails.setStreet(guest.getStreet());
        guestPersonalDetails.setEmail(guest.getEmail());
        guestPersonalDetails.setPhone(guest.getPhone());
        guestPersonalDetails.setPassportId(guest.getPassportId());

        StayDto stayDto = new StayDto();
        stayDto.setGuestPersonalDetails(guestPersonalDetails);

        return stayDto;
    };

    @Autowired
    private IGuestService guestService;

    @GetMapping
    public GuestOverviewDto getGuests(@RequestParam(required = false, defaultValue = "2") int checkedIn) {
        GuestFilterType guestFilterType;
        switch (checkedIn) {
            case 0:
                guestFilterType = GuestFilterType.NOT_CHECKED_IN;
                break;
            case 1:
                guestFilterType = GuestFilterType.CHECKED_IN;
                break;
            default:
                guestFilterType = GuestFilterType.ALL;
                break;
        }
        List<Guest> guests = guestService.getAllGuests(guestFilterType);
        return new GuestOverviewDto(guests.stream().map(MAP_GUEST_TO_GUEST_DTO).collect(Collectors.toList()));
    }

    @GetMapping("/{guestId}")
    public GuestDetailDto getGuestDetails(@PathVariable long guestId) {
        GuestDetailDto resultGuestDetailDto = new GuestDetailDto();

        Guest guest = guestService.getGuestById(guestId);
        if (guest.isCheckedin()) {
            Stay currentStay = guest.getStays().get(0);
            resultGuestDetailDto.setStayDto(MAP_STAY_TO_STAY_DTO.apply(currentStay));
        } else {
            resultGuestDetailDto.setStayDto(MAP_GUEST_TO_STAY_DTO.apply(guest));
        }
        guest.getStays().forEach(resultGuestDetailDto::addStaySummary);

        return resultGuestDetailDto;
    }

    @PutMapping
    public void updatePersonalDetails(@RequestBody GuestPersonalDetailsDto personalDetailsDto) {
        guestService.updatePersonalDetails(personalDetailsDto);
    }

    @PostMapping
    public void checkIn(@RequestBody CheckInDto checkInDto) {
        guestService.checkIn(checkInDto);
    }

    @GetMapping("/{guestId}/personal-details")
    public GuestPersonalDetailsDto getGuestPersonalDetails(@PathVariable long guestId) {
        Guest guest = guestService.getGuestById(guestId);
        return mapGuestToGuestPersonalDetailsDto(guest);
    }

    private GuestPersonalDetailsDto mapGuestToGuestPersonalDetailsDto(Guest guest) {
        return null;
    }
}

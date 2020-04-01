package com.tabit.dcm2.controller;

import com.tabit.dcm2.controller.util.MapperUtil;
import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.service.GuestFilterType;
import com.tabit.dcm2.service.IGuestService;
import com.tabit.dcm2.service.dto.GuestDetailDto;
import com.tabit.dcm2.service.dto.GuestDto;
import com.tabit.dcm2.service.dto.GuestOverviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class GuestController {
    private static final Function<Guest, GuestDto> GUEST_TO_GUEST_DTO = guest -> {
        GuestDto guestDto = new GuestDto();
        guestDto.setId(guest.getId());
        if(guest.isCheckedin()){
            guestDto.setBoxNumber(guest.getStays().get(0).getBoxNumber());
        }
        guestDto.setFirstName(guest.getFirstName());
        guestDto.setLastName(guest.getLastName());
        guestDto.setCheckedin(guest.isCheckedin());
        return guestDto;
    };

    @Autowired
    private IGuestService guestService;

    @RequestMapping(path = "/api/guests")
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
        return new GuestOverviewDto(guests.stream().map(GUEST_TO_GUEST_DTO).collect(Collectors.toList()));
    }

    @RequestMapping(path = "/api/guests/{guestId}")
    public GuestDetailDto getGuestDetails(@PathVariable() long guestId) {
        GuestDetailDto resultGuestDetailDto = new GuestDetailDto();

        Guest guest = guestService.getGuestById(guestId);
        if (guest.isCheckedin()){
            Stay currentStay = guest.getStays().get(0);
            resultGuestDetailDto.setStayDto(MapperUtil.mapStayToStayDto(currentStay));
        } else {
            resultGuestDetailDto.setStayDto(MapperUtil.mapGuestToStayDto(guest));
        }
        guest.getStays().forEach(resultGuestDetailDto::addStaySummary);

        return resultGuestDetailDto;
    }
}

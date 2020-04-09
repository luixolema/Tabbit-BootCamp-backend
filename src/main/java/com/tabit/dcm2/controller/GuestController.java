package com.tabit.dcm2.controller;

import com.tabit.dcm2.controller.util.MapperUtil;
import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.service.GuestFilterType;
import com.tabit.dcm2.service.IGuestService;
import com.tabit.dcm2.service.dto.GuestDetailDto;
import com.tabit.dcm2.service.dto.GuestDto;
import com.tabit.dcm2.service.dto.GuestOverviewDto;
import com.tabit.dcm2.service.dto.GuestPersonalDetailsDto;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/guests")
public class GuestController {

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

        List<GuestDto> guestDtos = new ArrayList<>();
        for (Guest guest : guests) { guestDtos.add(MapperUtil.guestToGuestDTO(guest)); }

        return new GuestOverviewDto(guestDtos);
    }

    @GetMapping("/{guestId}")
    public GuestDetailDto getGuestDetails(@PathVariable long guestId) {
        GuestDetailDto resultGuestDetailDto = new GuestDetailDto();

        Guest guest = guestService.getGuestById(guestId);
        if (guest.isCheckedin()) {
            Stay currentStay = guest.getStays().get(0);
            resultGuestDetailDto.setStayDto(MapperUtil.mapStayToStayDto(currentStay));
        } else {
            resultGuestDetailDto.setStayDto(MapperUtil.mapGuestToStayDto(guest));
        }
        guest.getStays().forEach(resultGuestDetailDto::addStaySummary);

        return resultGuestDetailDto;
    }

    @PutMapping
    public void updatePersonalDetails(@RequestBody GuestPersonalDetailsDto personalDetailsDto) {
        guestService.updatePersonalDetails(personalDetailsDto);
    }
}

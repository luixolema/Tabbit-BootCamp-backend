package com.tabit.dcm2.controller;

import com.tabit.dcm2.service.GuestFilterType;
import com.tabit.dcm2.service.GuestsDto;
import com.tabit.dcm2.service.IGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class GuestController {
    @Autowired
    private IGuestService guestService;

    @RequestMapping(path="/api/guests")
    public GuestsDto getGuests(@RequestParam(required = false, defaultValue = "2") int checkIn) {
        GuestFilterType guestFilterType;
        switch (checkIn) {
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
        return guestService.getGuests(guestFilterType);
    }
}

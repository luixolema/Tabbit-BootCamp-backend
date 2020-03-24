package com.tabit.dcm2.controller;

import com.tabit.dcm2.service.GuestsDto;
import com.tabit.dcm2.service.IGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GuestController {
    @Autowired
    private IGuestService guestService;

    @RequestMapping(path="/api/guests")
    public GuestsDto getGuests(@RequestParam(required = false) Boolean checkIn) {
        return guestService.getGuests(checkIn);
    }
}

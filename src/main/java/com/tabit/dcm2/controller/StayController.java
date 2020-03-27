package com.tabit.dcm2.controller;

import com.tabit.dcm2.service.GuestDetailDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class StayController {

    @RequestMapping(path = "/api/stay/{stayId}")
    public GuestDetailDto getStay(@PathVariable() Long stayId) {
        return null;
    }
}

package com.tabit.dcm2.controller;

import com.tabit.dcm2.controller.util.MapperUtil;
import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.service.IGuestService;
import com.tabit.dcm2.service.IStayService;
import com.tabit.dcm2.service.dto.StayDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/stay")
public class StayController {
    @Autowired
    private IStayService stayService;

    @Autowired
    private IGuestService guestService;

    @GetMapping(path = "/{stayId}")
    public StayDto getStay(@PathVariable() Long stayId) {
        return MapperUtil.mapStayToStayDto(stayService.findById(stayId));
    }

    @PostMapping
    public void updateStay(@RequestBody StayDto stayDto) {
        Guest newVersionGuest = MapperUtil.mapStayDtoToGuest(stayDto);
        Guest oldVersionGuest = guestService.getGuestById(newVersionGuest.getId());
        newVersionGuest.setCheckedin(oldVersionGuest.isCheckedin());
        newVersionGuest.setStays(oldVersionGuest.getStays());

        guestService.updateGuest(newVersionGuest);
        if (newVersionGuest.isCheckedin()) {
            Stay newVersionStay = MapperUtil.mapStayDtoToStay(stayDto);
            newVersionStay.setId(newVersionGuest.getStays().get(0).getId());
            newVersionStay.setGuest(newVersionGuest);
            newVersionStay.setActive(true);

            stayService.updateStay(newVersionStay);
        }
    }
}

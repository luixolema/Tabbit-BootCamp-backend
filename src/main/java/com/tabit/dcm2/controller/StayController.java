package com.tabit.dcm2.controller;

import com.tabit.dcm2.controller.util.MapperUtil;
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

    @GetMapping(path = "/{stayId}")
    public StayDto getStay(@PathVariable() Long stayId) {
        return MapperUtil.mapStayToStayDto(stayService.findById(stayId));
    }

    @PutMapping
    public void updateStay(@RequestBody StayDto stayDto) {
    }
}

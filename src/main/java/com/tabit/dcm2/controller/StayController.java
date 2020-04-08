package com.tabit.dcm2.controller;

import com.tabit.dcm2.controller.util.BillMapper;
import com.tabit.dcm2.controller.util.MapperUtil;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.service.IStayService;
import com.tabit.dcm2.service.dto.BillDto;
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

    @PostMapping
    public void updateStay(@RequestBody StayDto stayDto) {
    }

    @GetMapping("/{stayId}/bill")
    public BillDto getBill(@PathVariable long stayId) {
        Stay stay = this.stayService.findById(stayId);
        return BillMapper.billToBillDto(stay.getBill());
    }
}

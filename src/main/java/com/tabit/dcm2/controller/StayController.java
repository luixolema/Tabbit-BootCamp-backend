package com.tabit.dcm2.controller;

import com.tabit.dcm2.entity.Stay;
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
        return mapStayToStayDto(stayService.findById(stayId));
    }

    private StayDto mapStayToStayDto(Stay stay) {
        StayDto stayDto = new StayDto();
        stayDto.setId(stay.getId());
        stayDto.setGuest(stay.getGuest());
        stayDto.setFirstName(stay.getFirstName());
        stayDto.setLastName(stay.getLastName());
        stayDto.setBoxNumber(stay.getBoxNumber());
        stayDto.setBirthDate(stay.getBirthDate());
        stayDto.setNationality(stay.getNationality());
        stayDto.setCountry(stay.getCountry());
        stayDto.setCity(stay.getCity());
        stayDto.setPostcode(stay.getPostcode());
        stayDto.setStreet(stay.getStreet());
        stayDto.setEmail(stay.getEmail());
        stayDto.setPhone(stay.getPhone());
        stayDto.setPassportId(stay.getPassportId());
        stayDto.setCheckInDate(stay.getCheckInDate());
        stayDto.setCheckOutDate(stay.getCheckOutDate());
        stayDto.setArriveDate(stay.getArriveDate());
        stayDto.setLeaveDate(stay.getLeaveDate());
        stayDto.setHotel(stay.getHotel());
        stayDto.setRoom(stay.getRoom());
        stayDto.setLastDiveDate(stay.getLastDiveDate());
        stayDto.setBrevet(stay.getBrevet());
        stayDto.setDivesAmount(stay.getDivesAmount());
        stayDto.setNitrox(stay.isNitrox());
        stayDto.setMedicalStatement(stay.isMedicalStatement());

        return stayDto;
    }
}

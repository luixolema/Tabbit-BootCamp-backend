package com.tabit.dcm2.controller;

import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.service.IStayService;
import com.tabit.dcm2.service.dto.GuestPersonalDetailsDto;
import com.tabit.dcm2.service.dto.StayDetailsDto;
import com.tabit.dcm2.service.dto.StayDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.function.Function;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/stay")
public class StayController {
    static Function<Stay, StayDto> MAP_STAY_TO_STAY_DTO = stay -> {
        GuestPersonalDetailsDto guestPersonalDetails = new GuestPersonalDetailsDto.Builder()
                .withId(stay.getGuest().getId())
                .withFirstName(stay.getFirstName())
                .withLastName(stay.getLastName())
                .withBirthDate(stay.getBirthDate())
                .withNationality(stay.getNationality())
                .withCountry(stay.getCountry())
                .withCity(stay.getCity())
                .withPostcode(stay.getPostcode())
                .withStreet(stay.getStreet())
                .withEmail(stay.getEmail())
                .withPhone(stay.getPhone())
                .withPassportId(stay.getPassportId())
                .build();

        StayDetailsDto stayDetails = new StayDetailsDto();
        stayDetails.setId(stay.getId());
        stayDetails.setBoxNumber(stay.getBoxNumber());
        stayDetails.setCheckInDate(stay.getCheckInDate());
        stayDetails.setCheckOutDate(stay.getCheckOutDate());
        stayDetails.setArriveDate(stay.getArriveDate());
        stayDetails.setLeaveDate(stay.getLeaveDate());
        stayDetails.setHotel(stay.getHotel());
        stayDetails.setRoom(stay.getRoom());
        stayDetails.setLastDiveDate(stay.getLastDiveDate());
        stayDetails.setBrevet(stay.getBrevet());
        stayDetails.setDivesAmount(stay.getDivesAmount());
        stayDetails.setNitrox(stay.isNitrox());
        stayDetails.setMedicalStatement(stay.isMedicalStatement());
        stayDetails.setActive(stay.isActive());
        stayDetails.setPreBooking(stay.getPreBooking());

        StayDto stayDto = new StayDto();
        stayDto.setGuestPersonalDetails(guestPersonalDetails);
        stayDto.setStayDetails(stayDetails);

        stayDto = stayDto.addLoanDetails(stay.getLoans());

        return stayDto;
    };

    @Autowired
    private IStayService stayService;

    @GetMapping("/{stayId}")
    public StayDto getStay(@PathVariable() Long stayId) {
        return MAP_STAY_TO_STAY_DTO.apply(stayService.findById(stayId));
    }

    @PutMapping
    public void updateStay(@RequestBody StayDto stayDto) {
        stayService.updateStay(stayDto);
    }

}

package com.tabit.dcm2.controller;

import com.tabit.dcm2.entity.Loan;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.service.IStayService;
import com.tabit.dcm2.service.dto.GuestPersonalDetailsDto;
import com.tabit.dcm2.service.dto.LoanDetailsDto;
import com.tabit.dcm2.service.dto.StayDetailsDto;
import com.tabit.dcm2.service.dto.StayDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/stay")
public class StayController {

    private static final Function<Loan, LoanDetailsDto> MAP_LOAN_TO_LOAN_DETAILS_DTO = loan -> new LoanDetailsDto.Builder()
            .withId(loan.getId())
            .withType(loan.getEquipment().getEquipmentType().getType())
            .withSerialNumber(loan.getEquipment().getSerialNumber())
            .withDateOut(loan.getDateOut())
            .withDateReturn(loan.getDateReturn())
            .build();

    static final Function<Stay, StayDto> MAP_STAY_TO_STAY_DTO = stay -> {
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

        StayDetailsDto stayDetails = new StayDetailsDto.Builder()
                .withId(stay.getId())
                .withBoxNumber(stay.getBoxNumber())
                .withCheckInDate(stay.getCheckInDate())
                .withCheckOutDate(stay.getCheckOutDate())
                .withArriveDate(stay.getArriveDate())
                .withLeaveDate(stay.getLeaveDate())
                .withHotel(stay.getHotel())
                .withRoom(stay.getRoom())
                .withLastDiveDate(stay.getLastDiveDate())
                .withBrevet(stay.getBrevet())
                .withDivesAmount(stay.getDivesAmount())
                .withNitrox(stay.isNitrox())
                .withMedicalStatement(stay.isMedicalStatement())
                .withActive(stay.isActive())
                .withPreBooking(stay.getPreBooking())
                .build();

        List<LoanDetailsDto> loanDetailsDtos = stay.getLoans().stream().map(MAP_LOAN_TO_LOAN_DETAILS_DTO).collect(Collectors.toList());

        return new StayDto.Builder()
                .withGuestPersonalDetails(guestPersonalDetails)
                .withStayDetails(stayDetails)
                .withLoanDetails(loanDetailsDtos)
                .build();
    };

    @Autowired
    private IStayService stayService;

    @GetMapping("/{stayId}")
    public StayDto getStay(@PathVariable Long stayId) {
        return MAP_STAY_TO_STAY_DTO.apply(stayService.findById(stayId));
    }

    @PutMapping
    public void updateStay(@RequestBody StayDto stayDto) {
        StayDto validatedDto = stayDto.copy();
        stayService.updateStay(validatedDto);
    }

}

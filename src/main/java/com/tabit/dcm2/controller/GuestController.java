package com.tabit.dcm2.controller;

import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.service.GuestFilterType;
import com.tabit.dcm2.service.IGuestService;
import com.tabit.dcm2.service.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.tabit.dcm2.controller.StayController.MAP_STAY_TO_STAY_DTO;

@RestController
@CrossOrigin
@RequestMapping("/api/guests")
public class GuestController {

    private static final Function<Guest, GuestDto> MAP_GUEST_TO_GUEST_DTO = guest ->
            new GuestDto.Builder()
                    .withId(guest.getId())
                    .withFirstName(guest.getFirstName())
                    .withLastName(guest.getLastName())
                    .withBoxNumber(guest.isCheckedin() ? guest.getStays().get(0).getBoxNumber() : null)
                    .withCheckedin(guest.isCheckedin())
                    .build();

    private static final Function<Guest, GuestPersonalDetailsDto> MAP_GUEST_TO_GUEST_PERSONAL_DETAILS_DTO = guest ->
            new GuestPersonalDetailsDto.Builder()
                    .withId(guest.getId())
                    .withPostcode(guest.getPostcode())
                    .withStreet(guest.getStreet())
                    .withPhone(guest.getPhone())
                    .withPassportId(guest.getPassportId())
                    .withLastName(guest.getLastName())
                    .withNationality(guest.getNationality())
                    .withFirstName(guest.getFirstName())
                    .withEmail(guest.getEmail())
                    .withCountry(guest.getCountry())
                    .withCity(guest.getCity())
                    .withBirthDate(guest.getBirthDate())
                    .build();

    private static final Function<Stay, StaySummaryDto> MAP_STAY_TO_STAY_SUMMARY_DTO_FUNCTION = stay -> new StaySummaryDto.Builder()
            .withId(stay.getId())
            .withArriveDate(stay.getArriveDate())
            .withLeaveDate(stay.getLeaveDate())
            .withActive(stay.isActive())
            .build();

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

        return new GuestOverviewDto.Builder()
                .withGuests(guests.stream().map(MAP_GUEST_TO_GUEST_DTO).collect(Collectors.toList()))
                .build();
    }

    @GetMapping("/{guestId}")
    public GuestDetailDto getGuestDetails(@PathVariable long guestId) {
        GuestDetailDto.Builder builder = new GuestDetailDto.Builder();

        Guest guest = guestService.getGuestById(guestId);
        if (guest.isCheckedin()) {
            Stay currentStay = guest.getStays().get(0);
            builder.withStayDto(MAP_STAY_TO_STAY_DTO.apply(currentStay));
        } else {
            builder.withGuestPersonalDetailsDto(MAP_GUEST_TO_GUEST_PERSONAL_DETAILS_DTO.apply(guest));
        }

        List<StaySummaryDto> staySummaries = guest.getStays().stream().map(MAP_STAY_TO_STAY_SUMMARY_DTO_FUNCTION).collect(Collectors.toList());
        return builder.withStaySummaries(staySummaries).build();
    }

    @PutMapping
    public void updatePersonalDetails(@RequestBody GuestPersonalDetailsDto personalDetailsDto) {
        GuestPersonalDetailsDto validated = personalDetailsDto.copy();
        guestService.updatePersonalDetails(validated);
    }

    @PostMapping("/check-in")
    public void checkIn(@RequestBody CheckInDto checkInDto) {
        CheckInDto validated = checkInDto.copy();
        guestService.checkIn(validated);
    }

    @GetMapping("/{guestId}/personal-details")
    public GuestPersonalDetailsDto getGuestPersonalDetails(@PathVariable long guestId) {
        Guest guest = guestService.getGuestById(guestId);
        return MAP_GUEST_TO_GUEST_PERSONAL_DETAILS_DTO.apply(guest);
    }

    @PostMapping
    public long create(@RequestBody GuestCreationDto guestCreationDto) {
        GuestCreationDto validatedGuestDto = guestCreationDto.copy();
        Guest guest = guestService.create(validatedGuestDto);
        return guest.getId();
    }
}

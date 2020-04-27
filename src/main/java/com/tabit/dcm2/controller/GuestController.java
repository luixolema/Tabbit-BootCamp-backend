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

    private static Function<Guest, GuestDto> MAP_GUEST_TO_GUEST_DTO = guest -> {
        GuestDto guestDto = new GuestDto.Builder()
                .withId(guest.getId())
                .withFirstName(guest.getFirstName())
                .withLastName(guest.getLastName())
                .withBoxNumber(guest.isCheckedin() ? guest.getStays().get(0).getBoxNumber() : null)
                .withCheckedin(guest.isCheckedin())
                .build();
        return guestDto;
    };

    private static Function<Guest, StayDto> MAP_GUEST_TO_STAY_DTO = guest -> {
        GuestPersonalDetailsDto guestPersonalDetails = new GuestPersonalDetailsDto.Builder()
                .withId(guest.getId())
                .withFirstName(guest.getFirstName())
                .withLastName(guest.getLastName())
                .withBirthDate(guest.getBirthDate())
                .withNationality(guest.getNationality())
                .withCountry(guest.getCountry())
                .withCity(guest.getCity())
                .withPostcode(guest.getPostcode())
                .withStreet(guest.getStreet())
                .withEmail(guest.getEmail())
                .withPhone(guest.getPhone())
                .withPassportId(guest.getPassportId())
                .build();

        StayDto stayDto = new StayDto();
        stayDto.setGuestPersonalDetails(guestPersonalDetails);

        return stayDto;
    };

    private static Function<Guest, GuestPersonalDetailsDto> MAP_GUEST_TO_GUEST_PERSONAL_DETAILS_DTO = guest ->
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

    public static Function<AbstractGuestPersonalDetailsDto, Guest> MAP_GUEST_PERSONAL_DETAILS_DTO_TO_GUEST = guestPersonalDetailDTO -> {
        Guest guest = new Guest();
        guest.setFirstName(guestPersonalDetailDTO.getFirstName());
        guest.setStreet(guestPersonalDetailDTO.getStreet());
        guest.setPostcode(guestPersonalDetailDTO.getPostcode());
        guest.setCountry(guestPersonalDetailDTO.getCountry());
        guest.setCity(guestPersonalDetailDTO.getCity());
        guest.setPhone(guestPersonalDetailDTO.getPhone());
        guest.setPassportId(guestPersonalDetailDTO.getPassportId());
        guest.setNationality(guestPersonalDetailDTO.getNationality());
        guest.setEmail(guestPersonalDetailDTO.getEmail());
        guest.setBirthDate(guestPersonalDetailDTO.getBirthDate());
        guest.setLastName(guestPersonalDetailDTO.getLastName());

        return guest;
    };

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
                .withTotal(guests.size())
                .build();
    }

    @GetMapping("/{guestId}")
    public GuestDetailDto getGuestDetails(@PathVariable long guestId) {
        GuestDetailDto resultGuestDetailDto = new GuestDetailDto();

        Guest guest = guestService.getGuestById(guestId);
        if (guest.isCheckedin()) {
            Stay currentStay = guest.getStays().get(0);
            resultGuestDetailDto.setStayDto(MAP_STAY_TO_STAY_DTO.apply(currentStay));
        } else {
            resultGuestDetailDto.setStayDto(MAP_GUEST_TO_STAY_DTO.apply(guest));
        }
        guest.getStays().forEach(resultGuestDetailDto::addStaySummary);

        return resultGuestDetailDto;
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
    public long create(@RequestBody CreationGuestDto creationGuestDto) {
        CreationGuestDto validatedGuestDto = creationGuestDto.copy();
        Guest guest = guestService.create(validatedGuestDto);
        return guest.getId();
    }
}

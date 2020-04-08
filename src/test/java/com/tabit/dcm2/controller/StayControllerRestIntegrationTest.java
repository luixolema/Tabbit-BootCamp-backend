package com.tabit.dcm2.controller;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.RandomStay;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.repository.IGuestRepo;
import com.tabit.dcm2.repository.IStayRepo;
import com.tabit.dcm2.service.dto.RandomStayDto;
import com.tabit.dcm2.service.dto.StayDto;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.tabit.dcm2.entity.RandomGuest.createGuestFromStayWithoutId;
import static org.assertj.core.api.Assertions.assertThat;

public class StayControllerRestIntegrationTest extends AbstractRestIntegrationTest {
    @Autowired
    private StayController stayController;

    @Autowired
    private IStayRepo stayRepo;

    @Autowired
    private IGuestRepo guestRepo;

    private Stay stay;
    private Guest guest;

    @Before
    public void setUp() {
        stay = RandomStay.createRandomStayWithoutId();
        guest = createGuestFromStayWithoutId(stay);
        guest.addStays(ImmutableList.of(stay));

        guestRule.persist(ImmutableList.of(guest));
    }

    @Test
    public void updateStay_can_serialize_all_fields_in_json_object() {
        // given
        StayDto stayDto = RandomStayDto.createRandomStayDto();
        stayDto.getStayDetails().setId(stay.getId());
        stayDto.getGuestPersonalDetails().setId(guest.getId());

        HttpEntity<StayDto> entity = createHttpEntity(stayDto);

        // when
        ResponseEntity<Void> response = restTemplate.exchange("/api/stay", HttpMethod.PUT, entity, Void.class);

        // then
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void updateStay_shall_update_data_when_guestDetails_is_also_updated() {
        // given
        StayDto stayDto = RandomStayDto.createStayDtoFromStay(stay);
        stayDto.getGuestPersonalDetails().setFirstName(stay.getFirstName() + "Update");
        stayDto.getGuestPersonalDetails().setBirthDate(stay.getBirthDate().minusDays(10));
        stayDto.getStayDetails().setHotel(stay.getHotel() + "Update");

        HttpEntity<StayDto> entity = createHttpEntity(stayDto);

        // when
        ResponseEntity<Void> response = restTemplate.exchange("/api/stay", HttpMethod.PUT, entity, Void.class);
        Guest newGuest = guestRepo.findById(stayDto.getGuestPersonalDetails().getId()).get();
        Stay newStay = stayRepo.findById(stayDto.getStayDetails().getId()).get();

        // then
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);
        assertThat(response.getBody()).isNull();

        assertThat(newStay.getFirstName()).isNotEqualTo(stay.getFirstName()).isEqualTo(stayDto.getGuestPersonalDetails().getFirstName());
        assertThat(newStay.getBirthDate()).isNotEqualTo(stay.getBirthDate()).isEqualTo(stayDto.getGuestPersonalDetails().getBirthDate());
        assertThat(newStay.getHotel()).isNotEqualTo(stay.getHotel()).isEqualTo(stayDto.getStayDetails().getHotel());

        assertThat(newStay.getId()).isEqualTo(stay.getId());
        assertThat(newStay.getGuest().getId()).isEqualTo(stay.getGuest().getId());
        assertThat(newStay.getLastName()).isEqualTo(stay.getLastName());
        assertThat(newStay.getCity()).isEqualTo(stay.getCity());
        assertThat(newStay.getCountry()).isEqualTo(stay.getCountry());
        assertThat(newStay.getEmail()).isEqualTo(stay.getEmail());
        assertThat(newStay.getNationality()).isEqualTo(stay.getNationality());
        assertThat(newStay.getPassportId()).isEqualTo(stay.getPassportId());
        assertThat(newStay.getPhone()).isEqualTo(stay.getPhone());
        assertThat(newStay.getPostcode()).isEqualTo(stay.getPostcode());
        assertThat(newStay.getArriveDate()).isEqualTo(stay.getArriveDate());
        assertThat(newStay.getBoxNumber()).isEqualTo(stay.getBoxNumber());
        assertThat(newStay.getBrevet()).isEqualTo(stay.getBrevet());
        assertThat(newStay.getCheckInDate()).isEqualTo(stay.getCheckInDate());
        assertThat(newStay.getCheckOutDate()).isEqualTo(stay.getCheckOutDate());
        assertThat(newStay.getLastDiveDate()).isEqualTo(stay.getLastDiveDate());
        assertThat(newStay.getLeaveDate()).isEqualTo(stay.getLeaveDate());
        assertThat(newStay.getDivesAmount()).isEqualTo(stay.getDivesAmount());
        assertThat(newStay.getRoom()).isEqualTo(stay.getRoom());
        assertThat(newStay.isNitrox()).isEqualTo(stay.isNitrox());
        assertThat(newStay.isMedicalStatement()).isEqualTo(stay.isMedicalStatement());
        assertThat(newStay.getPreBooking()).isEqualTo(stay.getPreBooking());

        assertThat(newGuest.getFirstName()).isNotEqualTo(guest.getFirstName()).isEqualTo(stayDto.getGuestPersonalDetails().getFirstName());
        assertThat(newGuest.getBirthDate()).isNotEqualTo(guest.getBirthDate()).isEqualTo(stayDto.getGuestPersonalDetails().getBirthDate());

        assertThat(newGuest.getId()).isEqualTo(guest.getId());
        assertThat(newGuest.getLastName()).isEqualTo(guest.getLastName());
        assertThat(newGuest.getNationality()).isEqualTo(guest.getNationality());
        assertThat(newGuest.getCountry()).isEqualTo(guest.getCountry());
        assertThat(newGuest.getCity()).isEqualTo(guest.getCity());
        assertThat(newGuest.getPostcode()).isEqualTo(guest.getPostcode());
        assertThat(newGuest.getStreet()).isEqualTo(guest.getStreet());
        assertThat(newGuest.getEmail()).isEqualTo(guest.getEmail());
        assertThat(newGuest.getPhone()).isEqualTo(guest.getPhone());
        assertThat(newGuest.getPassportId()).isEqualTo(guest.getPassportId());
    }

    @Test
    public void updateStay_shall_update_data_when_no_guestDetails_is_updated() {
        // given
        StayDto stayDto = RandomStayDto.createStayDtoFromStay(stay);
        stayDto.getStayDetails().setHotel(stay.getHotel() + "Update");

        HttpEntity<StayDto> entity = createHttpEntity(stayDto);

        // when
        ResponseEntity<Void> response = restTemplate.exchange("/api/stay", HttpMethod.PUT, entity, Void.class);
        Stay newStay = stayRepo.findById(stayDto.getStayDetails().getId()).get();

        // then
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);
        assertThat(response.getBody()).isNull();

        assertThat(newStay.getHotel()).isNotEqualTo(stay.getHotel()).isEqualTo(stayDto.getStayDetails().getHotel());

        assertThat(newStay.getId()).isEqualTo(stay.getId());
        assertThat(newStay.getGuest().getId()).isEqualTo(stay.getGuest().getId());
        assertThat(newStay.getFirstName()).isEqualTo(stay.getFirstName());
        assertThat(newStay.getLastName()).isEqualTo(stay.getLastName());
        assertThat(newStay.getBirthDate()).isEqualTo(stay.getBirthDate());
        assertThat(newStay.getCity()).isEqualTo(stay.getCity());
        assertThat(newStay.getCountry()).isEqualTo(stay.getCountry());
        assertThat(newStay.getEmail()).isEqualTo(stay.getEmail());
        assertThat(newStay.getNationality()).isEqualTo(stay.getNationality());
        assertThat(newStay.getPassportId()).isEqualTo(stay.getPassportId());
        assertThat(newStay.getPhone()).isEqualTo(stay.getPhone());
        assertThat(newStay.getPostcode()).isEqualTo(stay.getPostcode());
        assertThat(newStay.getArriveDate()).isEqualTo(stay.getArriveDate());
        assertThat(newStay.getBoxNumber()).isEqualTo(stay.getBoxNumber());
        assertThat(newStay.getBrevet()).isEqualTo(stay.getBrevet());
        assertThat(newStay.getCheckInDate()).isEqualTo(stay.getCheckInDate());
        assertThat(newStay.getCheckOutDate()).isEqualTo(stay.getCheckOutDate());
        assertThat(newStay.getLastDiveDate()).isEqualTo(stay.getLastDiveDate());
        assertThat(newStay.getLeaveDate()).isEqualTo(stay.getLeaveDate());
        assertThat(newStay.getDivesAmount()).isEqualTo(stay.getDivesAmount());
        assertThat(newStay.getRoom()).isEqualTo(stay.getRoom());
        assertThat(newStay.isNitrox()).isEqualTo(stay.isNitrox());
        assertThat(newStay.isMedicalStatement()).isEqualTo(stay.isMedicalStatement());
        assertThat(newStay.getPreBooking()).isEqualTo(stay.getPreBooking());

        Guest sameGuest = newStay.getGuest();


        assertThat(sameGuest.getId()).isEqualTo(guest.getId());
        assertThat(sameGuest.getFirstName()).isEqualTo(guest.getFirstName());
        assertThat(sameGuest.getLastName()).isEqualTo(guest.getLastName());
        assertThat(sameGuest.getBirthDate()).isEqualTo(guest.getBirthDate());
        assertThat(sameGuest.getNationality()).isEqualTo(guest.getNationality());
        assertThat(sameGuest.getCountry()).isEqualTo(guest.getCountry());
        assertThat(sameGuest.getCity()).isEqualTo(guest.getCity());
        assertThat(sameGuest.getPostcode()).isEqualTo(guest.getPostcode());
        assertThat(sameGuest.getStreet()).isEqualTo(guest.getStreet());
        assertThat(sameGuest.getEmail()).isEqualTo(guest.getEmail());
        assertThat(sameGuest.getPhone()).isEqualTo(guest.getPhone());
        assertThat(sameGuest.getPassportId()).isEqualTo(guest.getPassportId());
    }
}
package com.tabit.dcm2.controller;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.RandomGuest;
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

import static com.tabit.dcm2.testutils.GuestMappingAssertions.assertTwoGuestsForUpdate;
import static com.tabit.dcm2.testutils.StayMappingAssertions.assertTwoStaysWithGuest;
import static com.tabit.dcm2.testutils.StayMappingAssertions.assertTwoStaysWithoutGuest;
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
        guest = RandomGuest.createRandomGuestWitoutId();
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
        Guest specificGuest = RandomGuest.createSpecificGuestForStay(stay);
        StayDto stayDto = RandomStayDto.createStayDtoFromGuest(stay);
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
        assertTwoGuestsForUpdate(specificGuest, newGuest);
        assertTwoStaysWithGuest(stay, newStay);
    }

    @Test
    public void updateStay_shall_update_data_when_no_guestDetails_is_updated() {
        // given
        StayDto stayDto = RandomStayDto.createStayDtoFromGuest(stay);
        stayDto.getStayDetails().setHotel(stay.getHotel() + "Update");

        HttpEntity<StayDto> entity = createHttpEntity(stayDto);

        // when
        ResponseEntity<Void> response = restTemplate.exchange("/api/stay", HttpMethod.PUT, entity, Void.class);
        Stay newStay = stayRepo.findById(stayDto.getStayDetails().getId()).get();

        // then
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);
        assertThat(response.getBody()).isNull();
        assertTwoStaysWithoutGuest(stay, newStay);
    }
}
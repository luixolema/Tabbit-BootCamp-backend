package com.tabit.dcm2.controller;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.RandomStay;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.repository.IGuestRepo;
import com.tabit.dcm2.repository.IStayRepo;
import com.tabit.dcm2.service.dto.RandomGuestPersonalDetailsDto;
import com.tabit.dcm2.service.dto.RandomStayDto;
import com.tabit.dcm2.service.dto.StayDto;
import com.tabit.dcm2.testutils.GuestMappingAssertions;
import com.tabit.dcm2.testutils.StayMappingAssertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static com.tabit.dcm2.entity.RandomGuest.createGuestFromStayWithoutId;
import static com.tabit.dcm2.entity.RandomGuest.createRandomGuestWitoutId;
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

        //given
        stay = RandomStay.createRandomStayWithoutIdGivenActiveState(true);
        guest = createGuestFromStayWithoutId(stay);
        guest.setStays(ImmutableList.of(stay));

        guestRule.persist(ImmutableList.of(guest));
    }

    @Test
    public void getStay_shall_return_stay() {

        //when
        ResponseEntity<StayDto> response = restTemplate.getForEntity(getBaseUrl() + "/api/stay/" + stay.getId(), StayDto.class);

        //then
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);
        StayMappingAssertions.assertStayDto(response.getBody(), stay);
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
    public void updateStay_shall_update_also_guest_data_when_guestDetails_is_updated() {
        // given
        StayDto stayDto = RandomStayDto.createStayDtoFromStay(stay);
        stayDto.getGuestPersonalDetails().setFirstName(stay.getFirstName() + "Update");
        stayDto.getGuestPersonalDetails().setBirthDate(stay.getBirthDate().minusDays(10));
        stayDto.getStayDetails().setHotel(stay.getHotel() + "Update");

        HttpEntity<StayDto> entity = createHttpEntity(stayDto);

        // when
        ResponseEntity<Void> response = restTemplate.exchange("/api/stay", HttpMethod.PUT, entity, Void.class);
        Guest guestInDb = guestRepo.findById(stayDto.getGuestPersonalDetails().getId()).get();
        Stay stayInDb = stayRepo.findById(stayDto.getStayDetails().getId()).get();

        // then
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);
        assertThat(response.getBody()).isNull();

        StayMappingAssertions.assertStayDto(stayDto, stayInDb);
        GuestMappingAssertions.assertPersonalDetails(guestInDb, stayDto.getGuestPersonalDetails());
    }

    @Test
    public void updateStay_shall_update_only_stay_data_when_guestDetails_is_not_updated() {
        // given
        StayDto stayDto = RandomStayDto.createStayDtoFromStay(stay);
        stayDto.setGuestPersonalDetails(RandomGuestPersonalDetailsDto.createGuestPersonalDetailsDtoFromGuest(guest));

        HttpEntity<StayDto> entity = createHttpEntity(stayDto);

        // when
        ResponseEntity<Void> response = restTemplate.exchange("/api/stay", HttpMethod.PUT, entity, Void.class);

        Stay stayInDb = stayRepo.findById(stayDto.getStayDetails().getId()).get();
        Guest guestInDb = stayInDb.getGuest();

        // then
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);
        assertThat(response.getBody()).isNull();

        StayMappingAssertions.assertStayDto(stayDto, stayInDb);
        assertThat(guest.getFirstName()).isEqualTo(guestInDb.getFirstName());
        assertThat(guest.getLastName()).isEqualTo(guestInDb.getLastName());
        assertThat(guest.getCity()).isEqualTo(guestInDb.getCity());
        assertThat(guest.getBirthDate()).isEqualTo(guestInDb.getBirthDate());
        assertThat(guest.getCountry()).isEqualTo(guestInDb.getCountry());
        assertThat(guest.getEmail()).isEqualTo(guestInDb.getEmail());
        assertThat(guest.getNationality()).isEqualTo(guestInDb.getNationality());
        assertThat(guest.getPassportId()).isEqualTo(guestInDb.getPassportId());
        assertThat(guest.getPhone()).isEqualTo(guestInDb.getPhone());
        assertThat(guest.getPostcode()).isEqualTo(guestInDb.getPostcode());
        assertThat(guest.getStreet()).isEqualTo(guestInDb.getStreet());
    }

    @Test
    public void isBoxFree_shall_return_the_right_value() {
        // given
        HttpEntity<String> activeBox = createHttpEntity(stay.getBoxNumber());
        HttpEntity<String> emptyBox = createHttpEntity(stay.getBoxNumber() + "Update");

        // when
        ResponseEntity<Boolean> responseFalse = restTemplate.exchange("/api/stay/boxState", HttpMethod.POST, activeBox, Boolean.class);
        ResponseEntity<Boolean> responseTrue = restTemplate.exchange("/api/stay/boxState", HttpMethod.POST, emptyBox, Boolean.class);
        Boolean resultFalse = responseFalse.getBody();
        Boolean resultTrue = responseTrue.getBody();

        // then
        assertThat(resultFalse).isFalse();
        assertThat(resultTrue).isTrue();
    }
    @Test
    public void addActiveStay_shall_create_new_active_stay_and_update_guest_personeldetails_and_set_checked_in_flag_true() {
        // given
        Guest notCheckedInGuest = createRandomGuestWitoutId();
        notCheckedInGuest.setCheckedin(false);
        notCheckedInGuest.setStays(new ArrayList<>());
        guestRule.persist(ImmutableList.of(notCheckedInGuest));

        StayDto stayDto = RandomStayDto.createRandomStayDto();
        stayDto.getStayDetails().setId(null);
        stayDto.getGuestPersonalDetails().setId(notCheckedInGuest.getId());

        HttpEntity<StayDto> entity = createHttpEntity(stayDto);

        // when
        ResponseEntity<Void> response = restTemplate.exchange("/api/stay", HttpMethod.POST, entity, Void.class);

        Guest guestInDb = guestRepo.findById(notCheckedInGuest.getId()).get();

        // then
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);
        assertThat(response.getBody()).isNull();

        StayMappingAssertions.assertStayDto(stayDto, guestInDb.getStays().get(0));
        assertThat(guestInDb.isCheckedin()).isTrue();
        assertThat(stayDto.getGuestPersonalDetails().getFirstName()).isEqualTo(guestInDb.getFirstName());
        assertThat(stayDto.getGuestPersonalDetails().getLastName()).isEqualTo(guestInDb.getLastName());
        assertThat(stayDto.getGuestPersonalDetails().getCity()).isEqualTo(guestInDb.getCity());
        assertThat(stayDto.getGuestPersonalDetails().getBirthDate()).isEqualTo(guestInDb.getBirthDate());
        assertThat(stayDto.getGuestPersonalDetails().getCountry()).isEqualTo(guestInDb.getCountry());
        assertThat(stayDto.getGuestPersonalDetails().getEmail()).isEqualTo(guestInDb.getEmail());
        assertThat(stayDto.getGuestPersonalDetails().getNationality()).isEqualTo(guestInDb.getNationality());
        assertThat(stayDto.getGuestPersonalDetails().getPassportId()).isEqualTo(guestInDb.getPassportId());
        assertThat(stayDto.getGuestPersonalDetails().getPhone()).isEqualTo(guestInDb.getPhone());
        assertThat(stayDto.getGuestPersonalDetails().getPostcode()).isEqualTo(guestInDb.getPostcode());
        assertThat(stayDto.getGuestPersonalDetails().getStreet()).isEqualTo(guestInDb.getStreet());
    }

    @Test
    public void addActiveStay_shall_throw_exception_if_trying_to_add_active_stay_for_checked_in_guest() {
        // given
        guest.setCheckedin(true);
        guestRule.persist(ImmutableList.of(guest));
        StayDto stayDto = RandomStayDto.createRandomStayDto();
        stayDto.getGuestPersonalDetails().setId(guest.getId());

        HttpEntity<StayDto> entity = createHttpEntity(stayDto);

        // when
        ResponseEntity<Void> response = restTemplate.exchange("/api/stay", HttpMethod.POST, entity, Void.class);


        // then
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void addActiveStay_shall_throw_exception_if_trying_to_add_active_stay_for_not_existing_guest() {
        // given
        StayDto stayDto = RandomStayDto.createRandomStayDto();

        HttpEntity<StayDto> entity = createHttpEntity(stayDto);

        // when
        ResponseEntity<Void> response = restTemplate.exchange("/api/stay", HttpMethod.POST, entity, Void.class);

        // then
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void addActiveStay_shall_throw_exception_if_trying_to_add_active_stay_with_already_used_box_number() {
        // given
        guest.setCheckedin(false);
        stay.setActive(true);
        guestRule.persist(ImmutableList.of(guest));
        StayDto stayDto = RandomStayDto.createRandomStayDto();
        stayDto.getGuestPersonalDetails().setId(guest.getId());
        stayDto.getStayDetails().setBoxNumber(stay.getBoxNumber());

        HttpEntity<StayDto> entity = createHttpEntity(stayDto);

        // when
        ResponseEntity<Void> response = restTemplate.exchange("/api/stay", HttpMethod.POST, entity, Void.class);

        // then
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNull();
    }

}
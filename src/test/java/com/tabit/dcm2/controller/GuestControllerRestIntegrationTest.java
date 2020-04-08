package com.tabit.dcm2.controller;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.RandomGuest;
import com.tabit.dcm2.entity.RandomStay;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.service.dto.GuestDetailDto;
import com.tabit.dcm2.service.dto.GuestPersonalDetailsDto;
import com.tabit.dcm2.service.dto.RandomGuestPersonalDetailsDto;
import com.tabit.dcm2.testutils.GuestMappingAssertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.tabit.dcm2.testutils.GuestMappingAssertions.GuestDetailType.WITH_PERSONAL_AND_ACTUAL_STAY_AND_SUMMARY;
import static org.assertj.core.api.Assertions.assertThat;

public class GuestControllerRestIntegrationTest extends AbstractRestIntegrationTest {

    private Guest guestCheckedInTrue;
    private Guest guestCheckedInFalse;
    private Guest guestCheckedInFalse2;
    private Guest guestCheckedInFalseWithoutStay;
    private Stay stayOld;
    private Stay stayActual;

    @Before
    public void setUp() {
        // given
        stayOld = RandomStay.createRandomStayWithoutId();
        stayOld.setArriveDate(LocalDate.now().minusYears(5));
        stayActual = RandomStay.createRandomStayWithoutId();
        stayActual.setArriveDate(LocalDate.now().minusDays(10));

        guestCheckedInTrue = RandomGuest.createRandomGuestWitoutId();
        guestCheckedInTrue.setCheckedin(true);
        guestCheckedInTrue.addStays(ImmutableList.of(stayOld, stayActual));

        guestCheckedInFalse = RandomGuest.createRandomGuestWitoutId();
        guestCheckedInFalse.setCheckedin(false);
        guestCheckedInFalse2 = RandomGuest.createRandomGuestWitoutId();
        guestCheckedInFalse2.setCheckedin(false);
        guestCheckedInFalseWithoutStay = RandomGuest.createRandomGuestWitoutId();
        guestCheckedInFalseWithoutStay.setCheckedin(false);
        guestCheckedInFalseWithoutStay.setStays(new ArrayList<>());

        guestRule.persist(ImmutableList.of(guestCheckedInTrue, guestCheckedInFalse, guestCheckedInFalse2, guestCheckedInFalseWithoutStay));
    }

    @Test
    public void getGuestDetails_shall_return_stay_and_personal_details_from_actual_stay_for_checkedIn_guest() {
        // when
        ResponseEntity<GuestDetailDto> result = restTemplate.getForEntity(getBaseUrl() + "/api/guests/" + guestCheckedInTrue.getId(), GuestDetailDto.class);

        // then
        GuestMappingAssertions.assertGuestDetailDto(result.getBody(), guestCheckedInTrue, WITH_PERSONAL_AND_ACTUAL_STAY_AND_SUMMARY);
        assertThat(result.getBody().getStayDto().getStayDetails().getId()).isEqualTo(stayActual.getId());

//        printJson(result.getBody());
    }

    @Test
    public void updateGuest_can_serialize_all_fields_in_json_object() {
        // given
        GuestPersonalDetailsDto guestPersonalDetailsDto = RandomGuestPersonalDetailsDto.createRandomGuestPersonalDetailsDto();
        guestPersonalDetailsDto.setId(guestCheckedInTrue.getId());

        HttpEntity<GuestPersonalDetailsDto> entity = createHttpEntity(guestPersonalDetailsDto);

        // when
        ResponseEntity<Void> response = restTemplate.exchange("/api/guests", HttpMethod.POST, entity, Void.class);

        // then
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void updateGuest_shall_update_data() {
        // given
        GuestPersonalDetailsDto guestPersonalDetailsDto = new GuestPersonalDetailsDto();
        guestPersonalDetailsDto.setId(guestCheckedInTrue.getId());
        guestPersonalDetailsDto.setFirstName(guestCheckedInTrue.getFirstName() + "Update");
        guestPersonalDetailsDto.setBirthDate(guestCheckedInTrue.getBirthDate().minusDays(10));
//        printJson(guestPersonalDetailsDto);

        HttpEntity<GuestPersonalDetailsDto> httpEntity = createHttpEntity(guestPersonalDetailsDto);

        // when
        ResponseEntity<Void> response = restTemplate.exchange("/api/guests", HttpMethod.POST, httpEntity, Void.class);

        // then
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);
        assertThat(response.getBody()).isNull();

        // FIXME DCM2-71
        // assertThat only the updated fields in the stay have changed and all the other remains the same
        // if guestpersoneldetails changes also the guest has to be updated because this is the actual stay so there must be the same information as in the guest
    }
}
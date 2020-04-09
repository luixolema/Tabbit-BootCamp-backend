package com.tabit.dcm2.controller;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.tabit.dcm2.controller.util.MapperUtil;
import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.RandomGuest;
import com.tabit.dcm2.entity.RandomStay;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.service.IGuestService;
import com.tabit.dcm2.service.dto.*;
import com.tabit.dcm2.testutils.GuestMappingAssertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.tabit.dcm2.testutils.GuestMappingAssertions.GuestDetailType.WITH_PERSONAL_AND_ACTUAL_STAY_AND_SUMMARY;
import static com.tabit.dcm2.testutils.GuestMappingAssertions.GuestDetailType.WITH_PERSONAL_AND_NO_ACTUAL_STAY_AND_NO_SUMMARY;
import static com.tabit.dcm2.testutils.GuestMappingAssertions.GuestDetailType.WITH_PERSONAL_AND_NO_ACTUAL_STAY_AND_OLD_SUMMARY;
import static org.assertj.core.api.Assertions.assertThat;

public class GuestControllerRestIntegrationTest extends AbstractRestIntegrationTest {
    @Autowired
    private IGuestService guestService;

    private Guest guestCheckedInTrue;
    private Guest guestCheckedInFalse;
    private Guest guestCheckedInFalse2;
    private Guest guestCheckedInFalseWithoutStay;
    private Stay stayOld;
    private Stay stayActual;
    private List<Guest> allGuests;
    private List<Guest> checkedInGuests;
    private List<Guest> notCheckedInGuests;

    @Before
    public void setUp() {
        // given
        stayOld = RandomStay.createRandomStayWithoutId();
        stayOld.setArriveDate(LocalDate.now().minusYears(5));
        stayActual = RandomStay.createRandomStayWithoutId();
        stayActual.setArriveDate(LocalDate.now().minusDays(10));

        guestCheckedInTrue = RandomGuest.createRandomGuestWitoutId();
        guestCheckedInTrue.setCheckedin(true);
        guestCheckedInTrue.addStays(ImmutableList.of(stayActual, stayOld));

        guestCheckedInFalse = RandomGuest.createRandomGuestWitoutId();
        guestCheckedInFalse.setCheckedin(false);
        guestCheckedInFalse2 = RandomGuest.createRandomGuestWitoutId();
        guestCheckedInFalse2.setCheckedin(false);
        guestCheckedInFalseWithoutStay = RandomGuest.createRandomGuestWitoutId();
        guestCheckedInFalseWithoutStay.setCheckedin(false);
        guestCheckedInFalseWithoutStay.setStays(new ArrayList<>());

        allGuests = ImmutableList.of(guestCheckedInTrue, guestCheckedInFalse, guestCheckedInFalse2, guestCheckedInFalseWithoutStay);
        checkedInGuests = ImmutableList.of(guestCheckedInTrue);
        notCheckedInGuests = ImmutableList.of(guestCheckedInFalse, guestCheckedInFalse2, guestCheckedInFalseWithoutStay);

        guestRule.persist(allGuests);
    }

    @Test
    public void  getGuests_shall_return_all_guests_for_null_input_param() {
        //when
        ResponseEntity<GuestOverviewDto> response = restTemplate.getForEntity(getBaseUrl() + "/api/guests", GuestOverviewDto.class);

        //then
        assertThat(response.getBody().getTotal()).isEqualTo(allGuests.size());
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);
        GuestMappingAssertions.assertGuestOverviewDto(allGuests, response.getBody());

    }

    @Test
    public void getGuests_shall_return_checkedin_guests() {
        // when
        ResponseEntity<GuestOverviewDto> response = restTemplate.getForEntity(getBaseUrl() + "/api/guests/?checkedIn=1", GuestOverviewDto.class);

        // then
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);
        assertThat(response.getBody().getTotal()).isEqualTo(checkedInGuests.size());
        GuestMappingAssertions.assertGuestOverviewDto(checkedInGuests, response.getBody());
        for ( GuestDto guestDto : response.getBody().getGuests() ) { assertThat(guestDto.isCheckedin()).isTrue(); }
    }

    @Test
    public void getGuests_shall_return_not_checkedin_guests() {
        // when
        ResponseEntity<GuestOverviewDto> response = restTemplate.getForEntity(getBaseUrl() + "/api/guests/?checkedIn=0", GuestOverviewDto.class);

        // then
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);
        assertThat(response.getBody().getTotal()).isEqualTo(notCheckedInGuests.size());
        GuestMappingAssertions.assertGuestOverviewDto(notCheckedInGuests, response.getBody());
        for (GuestDto guestDto : response.getBody().getGuests()) { assertThat(guestDto.isCheckedin()).isFalse(); }
    }
    @Test
    public void getGuestDetails_shall_return_only_personal_details_from_guest_for_not_checkedIn_guest() {
        // when
        ResponseEntity<GuestDetailDto> response = restTemplate.getForEntity(getBaseUrl() + "/api/guests/" + guestCheckedInFalse.getId(), GuestDetailDto.class);

        // then
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);
        GuestMappingAssertions.assertGuestDetailDto(response.getBody(), guestCheckedInFalse, WITH_PERSONAL_AND_NO_ACTUAL_STAY_AND_OLD_SUMMARY);
        assertThat(response.getBody().getStayDto().getStayDetails()).isNull();
        assertThat(response.getBody().getStayDto().getGuestPersonalDetails().getId()).isEqualTo(guestCheckedInFalse.getId());
    }

    @Test
    public void getGuestDetails_shall_return_stay_and_personal_details_from_actual_stay_for_checkedIn_guest() {
        // when
        ResponseEntity<GuestDetailDto> response = restTemplate.getForEntity(getBaseUrl() + "/api/guests/" + guestCheckedInTrue.getId(), GuestDetailDto.class);

        // then
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);
        GuestMappingAssertions.assertGuestDetailDto(response.getBody(), guestCheckedInTrue, WITH_PERSONAL_AND_ACTUAL_STAY_AND_SUMMARY);
        assertThat(response.getBody().getStayDto().getStayDetails().getId()).isEqualTo(stayActual.getId());
        assertThat(response.getBody().getStayDto().getGuestPersonalDetails().getId()).isEqualTo(guestCheckedInTrue.getId());

//        printJson(result.getBody());
    }
    @Test
    public void getGuestDetails_shall_return_no_summary_for_not_checkedIn_guest_without_stays() {
        // when
        ResponseEntity<GuestDetailDto> response = restTemplate.getForEntity(getBaseUrl() + "/api/guests/" + guestCheckedInFalseWithoutStay.getId(), GuestDetailDto.class);
        // then
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);
        assertThat(response.getBody().getStayDto().getStayDetails()).isNull();
        assertThat(response.getBody().getStaySummaries()).isEmpty();
        GuestMappingAssertions.assertGuestDetailDto(response.getBody(), guestCheckedInFalseWithoutStay, WITH_PERSONAL_AND_NO_ACTUAL_STAY_AND_NO_SUMMARY);
    }

    @Test
    public void updateGuest_can_serialize_all_fields_in_json_object() {
        // given
        GuestPersonalDetailsDto guestPersonalDetailsDto = RandomGuestPersonalDetailsDto.createRandomGuestPersonalDetailsDto();
        guestPersonalDetailsDto.setId(guestCheckedInTrue.getId());

        HttpEntity<GuestPersonalDetailsDto> entity = createHttpEntity(guestPersonalDetailsDto);

        // when
        ResponseEntity<Void> response = restTemplate.exchange(
                "/api/guests/",
                HttpMethod.PUT,
                entity,
                Void.class
        );

        // then
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void updateGuest_shall_update_data() {
        // given
        GuestPersonalDetailsDto guestPersonalDetailsDto = RandomGuestPersonalDetailsDto.createRandomGuestPersonalDetailsDto();
        guestPersonalDetailsDto.setId(guestCheckedInFalse.getId());
        printJson(guestPersonalDetailsDto);

        HttpEntity<GuestPersonalDetailsDto> httpEntity = createHttpEntity(guestPersonalDetailsDto);

        // when
        ResponseEntity<Void> response = restTemplate.exchange(
                "/api/guests/",
                HttpMethod.PUT,
                httpEntity,
                Void.class
        );

        // then
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);
        assertThat(response.getBody()).isNull();

        Guest actualGuest = guestService.getGuestById(guestPersonalDetailsDto.getId());
        GuestMappingAssertions.assertPersonalDetails(actualGuest, guestPersonalDetailsDto);
    }
}
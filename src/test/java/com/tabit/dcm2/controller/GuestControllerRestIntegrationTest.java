package com.tabit.dcm2.controller;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.tabit.dcm2.entity.*;
import com.tabit.dcm2.service.IGuestService;
import com.tabit.dcm2.service.ILoginService;
import com.tabit.dcm2.service.dto.*;
import com.tabit.dcm2.testutils.GuestMappingAssertions;
import com.tabit.dcm2.testutils.StayMappingAssertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.tabit.dcm2.testutils.GuestMappingAssertions.GuestDetailType.WITH_PERSONAL_AND_ACTUAL_STAY_AND_SUMMARY;
import static com.tabit.dcm2.testutils.GuestMappingAssertions.GuestDetailType.WITH_PERSONAL_AND_NO_ACTUAL_STAY_AND_NO_SUMMARY;
import static com.tabit.dcm2.testutils.GuestMappingAssertions.GuestDetailType.WITH_PERSONAL_AND_NO_ACTUAL_STAY_AND_OLD_SUMMARY;
import static org.assertj.core.api.Assertions.assertThat;

public class GuestControllerRestIntegrationTest extends AbstractRestIntegrationTest {
    private static final Comparator<GuestDto> SORT_BY_ID = Comparator.comparing(GuestDto::getId);

    @Autowired
    private IGuestService guestService;

    @Autowired
    private ILoginService loginService;

    private Guest guestCheckedInTrue;
    private Guest guestCheckedInFalse;
    private Guest guestCheckedInFalse2;
    private Guest guestCheckedInFalseWithoutStay;
    private Stay stayOld;
    private Stay stayActual;
    private BoxManagement boxManagement;
    private User user;
    private String password = "password";
    private String authToken;

    @Before
    public void setUp() {
        // given
        DiveCenter diveCenter = RandomDiveCenter.createRandomDiveCenterWithoutId();
        diveCenterRule.persist(ImmutableList.of(diveCenter));

        stayOld = RandomStay.createRandomStayWithoutIdGivenDiveCenter(diveCenter);
        stayOld.setArriveDate(LocalDate.now().minusYears(5));
        stayOld.setActive(false);
        stayActual = RandomStay.createRandomStayWithoutIdGivenDiveCenter(diveCenter);
        stayActual.setArriveDate(LocalDate.now().minusDays(10));
        stayActual.setActive(true);

        boxManagement = RandomBoxManagement.createRandomBoxManagementWithoutIdGivenDiveCenter(diveCenter);
        boxManagement.setBoxNumber(stayActual.getBoxNumber());
        boxManagementRule.persist(ImmutableList.of(boxManagement));

        guestCheckedInTrue = RandomGuest.createRandomGuestWithoutIdGivenDiveCenter(diveCenter);
        guestCheckedInTrue.setCheckedin(true);
        guestCheckedInTrue.setStays(ImmutableList.of(stayActual, stayOld));

        guestCheckedInFalse = RandomGuest.createRandomGuestWithoutIdGivenDiveCenter(diveCenter);
        guestCheckedInFalse.setCheckedin(false);
        guestCheckedInFalse2 = RandomGuest.createRandomGuestWithoutIdGivenDiveCenter(diveCenter);
        guestCheckedInFalse2.setCheckedin(false);
        guestCheckedInFalseWithoutStay = RandomGuest.createRandomGuestWithoutIdGivenDiveCenter(diveCenter);
        guestCheckedInFalseWithoutStay.setCheckedin(false);
        guestCheckedInFalseWithoutStay.setStays(new ArrayList<>());

        guestRule.persist(ImmutableList.of(guestCheckedInTrue, guestCheckedInFalse, guestCheckedInFalse2, guestCheckedInFalseWithoutStay));

        user = RandomUser.createRandomUserWithPasswordWithoutId(password);
        user.setDiveCenter(diveCenter);
        userRule.persist(ImmutableList.of(user));

        authToken = loginService.generateJwtToken(user.getLogin(), password).getToken();
    }

    @Test
    public void getGuests_shall_return_all_guests_for_null_input_param() {
        // given
        HttpEntity<?> entity = createHttpEntity(authToken);

        // when
        ResponseEntity<GuestOverviewDto> response = restTemplate.exchange(
                "/api/guests/",
                HttpMethod.GET,
                entity,
                GuestOverviewDto.class
        );

        //then
        GuestOverviewDto actualGuestOverviewDto = response.getBody();
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);

        List<GuestDto> sorted = new ArrayList<>(actualGuestOverviewDto.getGuests());
        sorted.sort(SORT_BY_ID);

        GuestMappingAssertions.assertGuestDto(sorted.get(0), guestCheckedInTrue, true);
        GuestMappingAssertions.assertGuestDto(sorted.get(1), guestCheckedInFalse, false);
        GuestMappingAssertions.assertGuestDto(sorted.get(2), guestCheckedInFalse2, false);
        GuestMappingAssertions.assertGuestDto(sorted.get(3), guestCheckedInFalseWithoutStay, false);
        assertThat(actualGuestOverviewDto.getGuests()).hasSize(4).hasSize(actualGuestOverviewDto.getTotal());
    }

    @Test
    public void getGuests_shall_return_checkedin_guests() {
        // given
        HttpEntity<?> entity = createHttpEntity(authToken);

        // when
        ResponseEntity<GuestOverviewDto> response = restTemplate.exchange(
                "/api/guests/?checkedIn=1",
                HttpMethod.GET,
                entity,
                GuestOverviewDto.class
        );

        // then
        GuestOverviewDto actualGuestOverviewDto = response.getBody();
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);
        GuestMappingAssertions.assertGuestDto(Iterables.getOnlyElement(actualGuestOverviewDto.getGuests()), guestCheckedInTrue, true);
        assertThat(actualGuestOverviewDto.getGuests()).hasSize(1).hasSize(actualGuestOverviewDto.getTotal());
    }

    @Test
    public void getGuests_shall_return_not_checkedin_guests() {
        // given
        HttpEntity<?> entity = createHttpEntity(authToken);

        // when
        ResponseEntity<GuestOverviewDto> response = restTemplate.exchange(
                "/api/guests/?checkedIn=0",
                HttpMethod.GET,
                entity,
                GuestOverviewDto.class
        );

        // then
        GuestOverviewDto actualGuestOverviewDto = response.getBody();
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);
        List<GuestDto> sorted = new ArrayList<>(response.getBody().getGuests());
        sorted.sort(SORT_BY_ID);

        GuestMappingAssertions.assertGuestDto(sorted.get(0), guestCheckedInFalse, false);
        GuestMappingAssertions.assertGuestDto(sorted.get(1), guestCheckedInFalse2, false);
        GuestMappingAssertions.assertGuestDto(sorted.get(2), guestCheckedInFalseWithoutStay, false);
        assertThat(actualGuestOverviewDto.getGuests()).hasSize(3).hasSize(actualGuestOverviewDto.getTotal());
    }

    @Test
    public void getGuestDetails_shall_return_only_personal_details_from_guest_for_not_checkedIn_guest() {
        // given
        HttpEntity<?> entity = createHttpEntity(authToken);

        // when
        ResponseEntity<GuestDetailDto> response = restTemplate.exchange(
                "/api/guests/" + guestCheckedInFalse.getId(),
                HttpMethod.GET,
                entity,
                GuestDetailDto.class
        );

        // then
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);
        GuestMappingAssertions.assertGuestDetailDto(response.getBody(), guestCheckedInFalse, WITH_PERSONAL_AND_NO_ACTUAL_STAY_AND_OLD_SUMMARY);
    }

    @Test
    public void getGuestDetails_shall_return_stay_and_personal_details_from_actual_stay_for_checkedIn_guest() {
        // given
        HttpEntity<?> entity = createHttpEntity(authToken);

        // when
        ResponseEntity<GuestDetailDto> response = restTemplate.exchange(
                "/api/guests/" + guestCheckedInTrue.getId(),
                HttpMethod.GET,
                entity,
                GuestDetailDto.class
        );

        // then
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);
        GuestMappingAssertions.assertGuestDetailDto(response.getBody(), guestCheckedInTrue, WITH_PERSONAL_AND_ACTUAL_STAY_AND_SUMMARY);
        assertThat(response.getBody().getStayDto().get().getStayDetails().getId()).isEqualTo(stayActual.getId());

//        printJson(result.getBody());
    }

    @Test
    public void getGuestDetails_shall_return_no_summary_for_not_checkedIn_guest_without_stays() {
        // given
        HttpEntity<?> entity = createHttpEntity(authToken);

        // when
        ResponseEntity<GuestDetailDto> response = restTemplate.exchange(
                "/api/guests/" + guestCheckedInFalseWithoutStay.getId(),
                HttpMethod.GET,
                entity,
                GuestDetailDto.class
        );

        // then
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);
        assertThat(response.getBody().getStayDto()).isEmpty();
        assertThat(response.getBody().getStaySummaries()).isEmpty();
        GuestMappingAssertions.assertGuestDetailDto(response.getBody(), guestCheckedInFalseWithoutStay, WITH_PERSONAL_AND_NO_ACTUAL_STAY_AND_NO_SUMMARY);
    }

    @Test
    public void updatePersonalDetails_can_serialize_all_fields_in_json_object() {
        // given
        GuestPersonalDetailsDto guestPersonalDetailsDto = RandomGuestPersonalDetailsDto.createRandomGuestPersonalDetailsDtoBuilder()
                .withId(guestCheckedInTrue.getId())
                .build();

        HttpEntity<GuestPersonalDetailsDto> entity = createHttpEntity(guestPersonalDetailsDto, authToken);

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
    public void updatePersonalDetails_shall_update_data() {
        // given
        GuestPersonalDetailsDto guestPersonalDetailsDto = RandomGuestPersonalDetailsDto.createRandomGuestPersonalDetailsDtoBuilder()
                .withId(guestCheckedInTrue.getId())
                .build();
        printJson(guestPersonalDetailsDto);

        HttpEntity<GuestPersonalDetailsDto> httpEntity = createHttpEntity(guestPersonalDetailsDto, authToken);

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

    @Test
    public void checkIn_shall_create_new_active_stay_and_update_guest_personeldetails_and_set_checked_in_flag_true() {
        // given
        CheckInDto checkInDto = RandomCheckInDto.createRandomCheckInDtoBuilder()
                .withGuestPersonalDetails(RandomGuestPersonalDetailsDto.createRandomGuestPersonalDetailsDtoBuilder()
                        .withPhone("phone")
                        .withPassportId("passport")
                        .withId(guestCheckedInFalseWithoutStay.getId())
                        .build())
                .build();

        HttpEntity<CheckInDto> entity = createHttpEntity(checkInDto, authToken);

        // when
        ResponseEntity<Void> response = restTemplate.exchange("/api/guests/check-in", HttpMethod.POST, entity, Void.class);

        Guest guestInDb = guestService.getGuestById(guestCheckedInFalseWithoutStay.getId());

        // then
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);
        assertThat(response.getBody()).isNull();

        StayMappingAssertions.assertNewStayFromCheckInDto(guestInDb.getStays().get(0), checkInDto);
        GuestMappingAssertions.assertPersonalDetails(guestInDb, checkInDto.getGuestPersonalDetails());
    }

    @Test
    public void checkIn_shall_return_a_conflict_response_if_boxnumber_is_already_used() {
        // given
        CheckInDto checkInDto = RandomCheckInDto.createRandomCheckInDtoBuilder()
                .withGuestPersonalDetails(RandomGuestPersonalDetailsDto.createRandomGuestPersonalDetailsDtoBuilder()
                        .withPhone("phone")
                        .withPassportId("passport")
                        .withId(guestCheckedInFalseWithoutStay.getId())
                        .build())
                .withStayDetails(RandomStayDetailsForCheckInDto.createRandomStayDetailsForCheckInDtoBuilder().withBoxNumber(stayActual.getBoxNumber()).build())
                .build();

        HttpEntity<CheckInDto> entity = createHttpEntity(checkInDto, authToken);

        // when
        ResponseEntity<Void> response = restTemplate.exchange("/api/guests/check-in", HttpMethod.POST, entity, Void.class);


        // then
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.CONFLICT);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void getGuestPersonalDetails_return_the_correct_GuestPersonalDetailDto() {
        // given
        String url = String.format("/api/guests/%s/personal-details", guestCheckedInTrue.getId());
        HttpEntity<?> entity = createHttpEntity(authToken);

        // when
        ResponseEntity<GuestPersonalDetailsDto> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                GuestPersonalDetailsDto.class
        );

        // then
        GuestPersonalDetailsDto guestPersonalDetailsDto = response.getBody();
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);
        assertThat(guestPersonalDetailsDto).isNotNull();
        GuestMappingAssertions.assertPersonalDetails(guestCheckedInTrue, guestPersonalDetailsDto);
    }

    @Test
    public void create_can_serialize_all_fields_in_json_object() {
        // given
        GuestCreationDto guestCreationDto = RandomGuestCreationDto.createGuestCreationDto();

        HttpEntity<GuestCreationDto> entity = createHttpEntity(guestCreationDto, authToken);

        // when
        ResponseEntity<Long> response = restTemplate.exchange(
                "/api/guests",
                HttpMethod.POST,
                entity,
                Long.class
        );

        // then
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    public void create_save_the_new_guest() {
        // given
        GuestCreationDto guestCreationDto = RandomGuestCreationDto.createGuestCreationDto();
        HttpEntity<GuestCreationDto> entity = createHttpEntity(guestCreationDto, authToken);

        // when
        ResponseEntity<Long> response = restTemplate.exchange(
                "/api/guests",
                HttpMethod.POST,
                entity,
                Long.class
        );
        Long createdGuestId = response.getBody();
        Guest currentSavedGuest = guestService.getGuestById(createdGuestId);

        // then
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);
        assertThat(createdGuestId).isNotNull();
        GuestMappingAssertions.assertPersonalDetailsWithoutId(currentSavedGuest, guestCreationDto);
    }

}
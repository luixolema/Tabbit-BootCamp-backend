package com.tabit.dcm2.controller;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.*;
import com.tabit.dcm2.service.dto.*;
import com.tabit.dcm2.testutils.BillMappingAssertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class StayControllerRestIntegrationTest extends AbstractRestIntegrationTest {
    @Autowired
    private StayController stayController;

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
        ResponseEntity<Void> response = restTemplate.exchange("/api/stay", HttpMethod.POST, entity, Void.class);

        // then
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);
    }

    @Test
    public void updateStay_shall_update_data_when_personalData_is_also_updated() {
        // given
        GuestPersonalDetailsDto guestPersonalDetailsDto = new GuestPersonalDetailsDto();
        guestPersonalDetailsDto.setId(guest.getId());
        guestPersonalDetailsDto.setFirstName(guest.getFirstName() + "Update");
        guestPersonalDetailsDto.setBirthDate(guest.getBirthDate().minusDays(10));

        StayDetailsDto stayDetailsDto = new StayDetailsDto();
        stayDetailsDto.setId(stay.getId());
        stayDetailsDto.setHotel(stay.getHotel() + "Update");

        StayDto stayDto = new StayDto();
        stayDto.setStayDetails(stayDetailsDto);
        stayDto.setGuestPersonalDetails(guestPersonalDetailsDto);

        HttpEntity<StayDto> entity = createHttpEntity(stayDto);

        // when
        ResponseEntity<Void> response = restTemplate.exchange("/api/stay", HttpMethod.POST, entity, Void.class);

        // then
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);
        assertThat(response.getBody()).isNull();

        // FIXME DCM2-71
        // assertThat only the updated fields in the stay have changed and all the other remains the same
        // if guestpersoneldetails changes also the guest has to be updated because this is the actual stay so there must be the same information as in the guest
    }

    @Test
    public void updateStay_shall_update_data_when_no_personalData_is_updated() {
        // given
        StayDetailsDto stayDetailsDto = new StayDetailsDto();
        stayDetailsDto.setId(stay.getId());
        stayDetailsDto.setHotel(stay.getHotel() + "Update");

        StayDto stayDto = new StayDto();
        stayDto.setStayDetails(stayDetailsDto);

        HttpEntity<StayDto> entity = createHttpEntity(stayDto);

        // when
        ResponseEntity<Void> response = restTemplate.exchange("/api/stay", HttpMethod.POST, entity, Void.class);

        // then
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);
        assertThat(response.getBody()).isNull();

        // FIXME DCM2-71
        // assertThat only the updated fields in the stay have changed and all the other remains the same
        // if guestpersoneldetails changes also the guest has to be updated because this is the actual stay so there must be the same information as in the guest
    }

    @Test
    public void getBill_shall_return_billDto() {
        // given
        Stay randomStay = RandomStay.createRandomStayWithoutId();
        randomStay.setGuest(guest);

        // add some mocked activities to test the total (latter)

        this.stayRule.persist(ImmutableList.of(randomStay));
        Bill bill = RandomBill.createRandomBillWithoutId();
        bill.setStay(randomStay);
        this.billRule.persist(ImmutableList.of(bill));


        // when
        ResponseEntity<BillDto> responseEntity = restTemplate.getForEntity(getBaseUrl() + "/api/stay/" + randomStay.getId() + "/bill", BillDto.class);

        // then
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getStatusCode().toString()).isEqualTo(HttpStatus.OK.toString());
        BillMappingAssertions.assertBillDto(responseEntity.getBody(), bill);
    }

    @Test
    public void getBill_shall_throw_respond_404_when_not_resource_found() {
        // when
        ResponseEntity<BillDto> responseEntity = restTemplate.getForEntity(getBaseUrl() + "/api/stay/-1/bill", BillDto.class);

        // then
        assertThat(responseEntity.getStatusCode().toString()).isEqualTo(HttpStatus.NOT_FOUND.toString());
    }
}
package com.tabit.dcm2.controller;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.RandomGuest;
import com.tabit.dcm2.entity.RandomStay;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.repository.AbstractRepoDbTest;
import com.tabit.dcm2.service.dto.StayDto;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class StayControllerIntegrationTest extends AbstractRepoDbTest {
    @Autowired
    private StayController stayController;

    private Stay stay;

    @Before
    public void setUp() {
        // given
        stay = RandomStay.createRandomStayWithoutId();
        Guest guest = RandomGuest.createRandomGuestWitoutId();
        guest.addStays(ImmutableList.of(stay));

        guestRule.persist(ImmutableList.of(guest));
    }

    @Test
    public void getStay_shall_return_stay() {
        // when
        StayDto stayDto = stayController.getStay(stay.getId());

        // then
        assertStayDto(stayDto, stay, stayDto.getGuest().getId());
    }

    private void assertStayDto(StayDto stayDto, Stay stay, Long expectedGuestId) {
        assertThat(stayDto.getId()).isEqualTo(stay.getId());
        assertThat(stayDto.getFirstName()).isEqualTo(stay.getFirstName());
        assertThat(stayDto.getLastName()).isEqualTo(stay.getLastName());
        assertThat(stayDto.getBirthDate()).isEqualTo(stay.getBirthDate());
        assertThat(stayDto.getCity()).isEqualTo(stay.getCity());
        assertThat(stayDto.getCountry()).isEqualTo(stay.getCountry());
        assertThat(stayDto.getEmail()).isEqualTo(stay.getEmail());
        assertThat(stayDto.getNationality()).isEqualTo(stay.getNationality());
        assertThat(stayDto.getPassportId()).isEqualTo(stay.getPassportId());
        assertThat(stayDto.getPhone()).isEqualTo(stay.getPhone());
        assertThat(stayDto.getPostcode()).isEqualTo(stay.getPostcode());
        assertThat(stayDto.getArriveDate()).isEqualTo(stay.getArriveDate());
        assertThat(stayDto.getBoxNumber()).isEqualTo(stay.getBoxNumber());
        assertThat(stayDto.getBrevet()).isEqualTo(stay.getBrevet());
        assertThat(stayDto.getCheckInDate()).isEqualTo(stay.getCheckInDate());
        assertThat(stayDto.getCheckOutDate()).isEqualTo(stay.getCheckOutDate());
        assertThat(stayDto.getLastDiveDate()).isEqualTo(stay.getLastDiveDate());
        assertThat(stayDto.getLeaveDate()).isEqualTo(stay.getLeaveDate());
        assertThat(stayDto.getDivesAmount()).isEqualTo(stay.getDivesAmount());
        assertThat(stayDto.getHotel()).isEqualTo(stay.getHotel());
        assertThat(stayDto.getRoom()).isEqualTo(stay.getRoom());
        assertThat(stayDto.isNitrox()).isEqualTo(stay.isNitrox());
        assertThat(stayDto.isMedicalStatement()).isEqualTo(stay.isMedicalStatement());

        assertThat(stayDto.getGuest().getId()).isEqualTo(expectedGuestId);
    }
}
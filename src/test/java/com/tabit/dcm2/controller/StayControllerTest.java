package com.tabit.dcm2.controller;

import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.RandomGuest;
import com.tabit.dcm2.entity.RandomStay;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.service.dto.StayDto;
import com.tabit.dcm2.service.impl.StayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StayControllerTest {
    @Mock
    private StayService stayService;

    @InjectMocks
    private StayController stayController;

    @Test
    public void getStay_shall_return_stay() {
        // given
        Guest guest = RandomGuest.createRandomGuest();
        Stay randomStay = RandomStay.createRandomStayWithoutId();
        randomStay.setGuest(guest);
        when(stayService.findById(randomStay.getId())).thenReturn(randomStay);

        // when
        StayDto stayDto = stayController.getStay(randomStay.getId());

        // then
        assertStayDto(stayDto, randomStay, randomStay.getGuest().getId());
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

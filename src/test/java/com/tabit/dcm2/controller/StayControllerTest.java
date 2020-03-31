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
        Stay randomStay = RandomStay.createRandomStay();
        randomStay.setGuest(guest);
        when(stayService.findById(randomStay.getId())).thenReturn(randomStay);

        // when
        StayDto stayDto = stayController.getStay(randomStay.getId());

        // then
        assertStayDto(stayDto, randomStay);
    }

    private void assertStayDto(StayDto stayDto, Stay stay) {
        assertThat(stayDto.getId()).isEqualTo(stay.getId());
        assertThat(stayDto.getGuestPersonalDetails().getGuestId()).isEqualTo(stay.getGuest().getId());
        assertThat(stayDto.getGuestPersonalDetails().getFirstName()).isEqualTo(stay.getFirstName());
        assertThat(stayDto.getGuestPersonalDetails().getLastName()).isEqualTo(stay.getLastName());
        assertThat(stayDto.getGuestPersonalDetails().getBirthDate()).isEqualTo(stay.getBirthDate());
        assertThat(stayDto.getGuestPersonalDetails().getCity()).isEqualTo(stay.getCity());
        assertThat(stayDto.getGuestPersonalDetails().getCountry()).isEqualTo(stay.getCountry());
        assertThat(stayDto.getGuestPersonalDetails().getEmail()).isEqualTo(stay.getEmail());
        assertThat(stayDto.getGuestPersonalDetails().getNationality()).isEqualTo(stay.getNationality());
        assertThat(stayDto.getGuestPersonalDetails().getPassportId()).isEqualTo(stay.getPassportId());
        assertThat(stayDto.getGuestPersonalDetails().getPhone()).isEqualTo(stay.getPhone());
        assertThat(stayDto.getGuestPersonalDetails().getPostcode()).isEqualTo(stay.getPostcode());

        assertThat(stayDto.getStayDetails().getArriveDate()).isEqualTo(stay.getArriveDate());
        assertThat(stayDto.getStayDetails().getBoxNumber()).isEqualTo(stay.getBoxNumber());
        assertThat(stayDto.getStayDetails().getBrevet()).isEqualTo(stay.getBrevet());
        assertThat(stayDto.getStayDetails().getCheckInDate()).isEqualTo(stay.getCheckInDate());
        assertThat(stayDto.getStayDetails().getCheckOutDate()).isEqualTo(stay.getCheckOutDate());
        assertThat(stayDto.getStayDetails().getLastDiveDate()).isEqualTo(stay.getLastDiveDate());
        assertThat(stayDto.getStayDetails().getLeaveDate()).isEqualTo(stay.getLeaveDate());
        assertThat(stayDto.getStayDetails().getDivesAmount()).isEqualTo(stay.getDivesAmount());
        assertThat(stayDto.getStayDetails().getHotel()).isEqualTo(stay.getHotel());
        assertThat(stayDto.getStayDetails().getRoom()).isEqualTo(stay.getRoom());
        assertThat(stayDto.getStayDetails().isNitrox()).isEqualTo(stay.isNitrox());
        assertThat(stayDto.getStayDetails().isMedicalStatement()).isEqualTo(stay.isMedicalStatement());
    }
}

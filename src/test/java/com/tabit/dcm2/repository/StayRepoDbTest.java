package com.tabit.dcm2.repository;
import com.tabit.dcm2.entity.Stay;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class StayRepoDbTest extends AbstractRepoDbTest {

    @Autowired
    private IStayRepo stayRepo;

    private void assertStay(Stay actualStay, Stay expectedStay, Long expectedGuestId) {
        assertThat(actualStay.getId()).isEqualTo(expectedStay.getId());
        assertThat(actualStay.getFirstName()).isEqualTo(expectedStay.getFirstName());
        assertThat(actualStay.getLastName()).isEqualTo(expectedStay.getLastName());
        assertThat(actualStay.getBirthDate()).isEqualTo(expectedStay.getBirthDate());
        assertThat(actualStay.getCity()).isEqualTo(expectedStay.getCity());
        assertThat(actualStay.getCountry()).isEqualTo(expectedStay.getCountry());
        assertThat(actualStay.getEmail()).isEqualTo(expectedStay.getEmail());
        assertThat(actualStay.getNationality()).isEqualTo(expectedStay.getNationality());
        assertThat(actualStay.getPassportId()).isEqualTo(expectedStay.getPassportId());
        assertThat(actualStay.getPhone()).isEqualTo(expectedStay.getPhone());
        assertThat(actualStay.getPostcode()).isEqualTo(expectedStay.getPostcode());
        assertThat(actualStay.getArriveDate()).isEqualTo(expectedStay.getArriveDate());
        assertThat(actualStay.getBoxNumber()).isEqualTo(expectedStay.getBoxNumber());
        assertThat(actualStay.getBrevet()).isEqualTo(expectedStay.getBrevet());
        assertThat(actualStay.getCheckInDate()).isEqualTo(expectedStay.getCheckInDate());
        assertThat(actualStay.getCheckOutDate()).isEqualTo(expectedStay.getCheckOutDate());
        assertThat(actualStay.getLastDiveDate()).isEqualTo(expectedStay.getLastDiveDate());
        assertThat(actualStay.getLeaveDate()).isEqualTo(expectedStay.getLeaveDate());
        assertThat(actualStay.getDivesAmount()).isEqualTo(expectedStay.getDivesAmount());
        assertThat(actualStay.getHotel()).isEqualTo(expectedStay.getHotel());
        assertThat(actualStay.getRoom()).isEqualTo(expectedStay.getRoom());
        assertThat(actualStay.isNitrox()).isEqualTo(expectedStay.isNitrox());
        assertThat(actualStay.isMedicalStatement()).isEqualTo(expectedStay.isMedicalStatement());

        assertThat(actualStay.getGuest().getId()).isEqualTo(expectedGuestId);
    }
}

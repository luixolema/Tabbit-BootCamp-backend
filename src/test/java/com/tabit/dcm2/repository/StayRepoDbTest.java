package com.tabit.dcm2.repository;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.RandomGuest;
import com.tabit.dcm2.entity.Stay;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class StayRepoDbTest extends AbstractDbTest {
    @Autowired
    private IStayRepo stayRepo;

    private Stay stay;

    @Before
    public void setUp() {
        // given
        Guest guestForStay = RandomGuest.createRandomGuestWitoutId();
        stay = Iterables.getOnlyElement(guestForStay.getStays());

        guestRule.persist(ImmutableList.of(guestForStay));
    }

    @Test
    public void findById_shall_return_the_stay() {
        // given
        stayRule.persist(ImmutableList.of(stay));

        //when
        Optional<Stay> actualStay = stayRepo.findById(stay.getId());

        //then
        assertThat(actualStay.isPresent()).isTrue();
        assertStay(actualStay.get(), stay);
    }

    @Test
    public void save_shall_save_the_stay() {

        //when
        stayRepo.save(stay);

        //then
        Optional<Stay> actualStay = stayRepo.findById(stay.getId());

        assertThat(actualStay.isPresent()).isTrue();
        assertStay(actualStay.get(), stay);
    }

    private void assertStay(Stay actualStay, Stay expectedStay) {
        assertThat(actualStay.getId()).isEqualTo(stay.getId());
        assertThat(actualStay.getFirstName()).isEqualTo(stay.getFirstName());
        assertThat(actualStay.getLastName()).isEqualTo(stay.getLastName());
        assertThat(actualStay.getBirthDate()).isEqualTo(stay.getBirthDate());
        assertThat(actualStay.getCity()).isEqualTo(stay.getCity());
        assertThat(actualStay.getCountry()).isEqualTo(stay.getCountry());
        assertThat(actualStay.getEmail()).isEqualTo(stay.getEmail());
        assertThat(actualStay.getNationality()).isEqualTo(stay.getNationality());
        assertThat(actualStay.getPassportId()).isEqualTo(stay.getPassportId());
        assertThat(actualStay.getPhone()).isEqualTo(stay.getPhone());
        assertThat(actualStay.getPostcode()).isEqualTo(stay.getPostcode());

        assertThat(actualStay.getArriveDate()).isEqualTo(stay.getArriveDate());
        assertThat(actualStay.getBoxNumber()).isEqualTo(stay.getBoxNumber());
        assertThat(actualStay.getBrevet()).isEqualTo(stay.getBrevet());
        assertThat(actualStay.getCheckInDate()).isEqualTo(stay.getCheckInDate());
        assertThat(actualStay.getCheckOutDate()).isEqualTo(stay.getCheckOutDate());
        assertThat(actualStay.getLastDiveDate()).isEqualTo(stay.getLastDiveDate());
        assertThat(actualStay.getLeaveDate()).isEqualTo(stay.getLeaveDate());
        assertThat(actualStay.getDivesAmount()).isEqualTo(stay.getDivesAmount());
        assertThat(actualStay.getHotel()).isEqualTo(stay.getHotel());
        assertThat(actualStay.getRoom()).isEqualTo(stay.getRoom());
        assertThat(actualStay.isNitrox()).isEqualTo(stay.isNitrox());
        assertThat(actualStay.isMedicalStatement()).isEqualTo(stay.isMedicalStatement());
        assertThat(actualStay.getPreBooking()).isEqualTo(stay.getPreBooking());
        assertThat(actualStay.isActive()).isEqualTo(stay.isActive());

        assertThat(actualStay.getGuest().getId()).isEqualTo(expectedStay.getGuest().getId());
    }
}

package com.tabit.dcm2.repository;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.RandomGuest;
import com.tabit.dcm2.entity.RandomStay;
import com.tabit.dcm2.entity.Stay;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class StayRepoDbTest extends AbstractDbTest {
    @Autowired
    private IStayRepo stayRepo;

    private Stay activeStay;
    private Stay activeStay2;
    private Stay notActiveStay;

    @Before
    public void setUp() {
        // given
        Guest guestForStay = RandomGuest.createRandomGuestWitoutId();
        activeStay = RandomStay.createRandomStayWithoutIdGivenActiveState(true);
        guestForStay.setStays(ImmutableList.of(activeStay));

        Guest guestForStay2 = RandomGuest.createRandomGuestWitoutId();
        activeStay2 = RandomStay.createRandomStayWithoutIdGivenActiveState(true);
        notActiveStay = RandomStay.createRandomStayWithoutIdGivenActiveState(false);
        guestForStay2.setStays(ImmutableList.of(activeStay2, notActiveStay));

        guestRule.persist(ImmutableList.of(guestForStay));
        guestRule.persist(ImmutableList.of(guestForStay2));
    }

    @Test
    public void findById_shall_return_the_stay() {
        // given
        stayRule.persist(ImmutableList.of(activeStay));

        //when
        Optional<Stay> actualStay = stayRepo.findById(activeStay.getId());

        //then
        assertThat(actualStay.isPresent()).isTrue();
        assertStay(actualStay.get(), activeStay);
    }

    @Test
    public void save_shall_save_the_stay() {

        //when
        stayRepo.save(activeStay);

        //then
        Optional<Stay> actualStay = stayRepo.findById(activeStay.getId());

        assertThat(actualStay.isPresent()).isTrue();
        assertStay(actualStay.get(), activeStay);
    }

    @Test
    public void getBoxNumbers_shall_return_list_of_empty_box_numbers() {

        //when
        List<String> boxNumbers = stayRepo.getBoxNumbers();

        //then
        assertThat(boxNumbers).hasSize(2);
        assertThat(boxNumbers).contains(activeStay.getBoxNumber());
        assertThat(boxNumbers).contains(activeStay2.getBoxNumber());
        assertThat(boxNumbers).doesNotContain(notActiveStay.getBoxNumber());
    }

    private void assertStay(Stay actualStay, Stay expectedStay) {
        assertThat(actualStay.getId()).isEqualTo(activeStay.getId());
        assertThat(actualStay.getFirstName()).isEqualTo(activeStay.getFirstName());
        assertThat(actualStay.getLastName()).isEqualTo(activeStay.getLastName());
        assertThat(actualStay.getBirthDate()).isEqualTo(activeStay.getBirthDate());
        assertThat(actualStay.getCity()).isEqualTo(activeStay.getCity());
        assertThat(actualStay.getCountry()).isEqualTo(activeStay.getCountry());
        assertThat(actualStay.getEmail()).isEqualTo(activeStay.getEmail());
        assertThat(actualStay.getNationality()).isEqualTo(activeStay.getNationality());
        assertThat(actualStay.getPassportId()).isEqualTo(activeStay.getPassportId());
        assertThat(actualStay.getPhone()).isEqualTo(activeStay.getPhone());
        assertThat(actualStay.getPostcode()).isEqualTo(activeStay.getPostcode());

        assertThat(actualStay.getArriveDate()).isEqualTo(activeStay.getArriveDate());
        assertThat(actualStay.getBoxNumber()).isEqualTo(activeStay.getBoxNumber());
        assertThat(actualStay.getBrevet()).isEqualTo(activeStay.getBrevet());
        assertThat(actualStay.getCheckInDate()).isEqualTo(activeStay.getCheckInDate());
        assertThat(actualStay.getCheckOutDate()).isEqualTo(activeStay.getCheckOutDate());
        assertThat(actualStay.getLastDiveDate()).isEqualTo(activeStay.getLastDiveDate());
        assertThat(actualStay.getLeaveDate()).isEqualTo(activeStay.getLeaveDate());
        assertThat(actualStay.getDivesAmount()).isEqualTo(activeStay.getDivesAmount());
        assertThat(actualStay.getHotel()).isEqualTo(activeStay.getHotel());
        assertThat(actualStay.getRoom()).isEqualTo(activeStay.getRoom());
        assertThat(actualStay.isNitrox()).isEqualTo(activeStay.isNitrox());
        assertThat(actualStay.isMedicalStatement()).isEqualTo(activeStay.isMedicalStatement());
        assertThat(actualStay.getPreBooking()).isEqualTo(activeStay.getPreBooking());
        assertThat(actualStay.isActive()).isEqualTo(activeStay.isActive());
    }
}

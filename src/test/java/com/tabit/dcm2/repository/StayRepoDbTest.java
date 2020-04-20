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

        guestRule.persist(ImmutableList.of(guestForStay, guestForStay2));
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
        List<String> boxNumbers = stayRepo.getActiveBoxNumbers();

        //then
        assertThat(boxNumbers).containsOnly(activeStay.getBoxNumber(), activeStay2.getBoxNumber());
    }

    private void assertStay(Stay actualStay, Stay expectedStay) {
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
        assertThat(actualStay.getPreBooking()).isEqualTo(expectedStay.getPreBooking());
        assertThat(actualStay.isActive()).isEqualTo(expectedStay.isActive());

        // REVIEW: loans should be checked here also (ordered by date_out)
        // also add the lost guest assert
    }
}

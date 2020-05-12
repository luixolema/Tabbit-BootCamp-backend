package com.tabit.dcm2.repository;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.tabit.dcm2.entity.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class GuestRepoDbTest extends AbstractDbTest {
    @Autowired
    private IGuestRepo guestRepo;
    private DiveCenter diveCenter1;
    private DiveCenter diveCenter2;


    @Before
    public void setUp() {
        //given
        diveCenter1 = RandomDiveCenter.createRandomDiveCenterWithoutId();
        diveCenter2 = RandomDiveCenter.createRandomDiveCenterWithoutId();
        diveCenterRule.persist(ImmutableList.of(diveCenter1, diveCenter2));
    }

    @Test
    public void findByCheckedIn_shall_return_the_guests() {
        // given

        Stay stayOld = RandomStay.createRandomStayWithoutIdGivenDiveCenter(diveCenter1);
        stayOld.setCheckInDate(LocalDate.now().minusYears(5));
        stayOld.setArriveDate(LocalDate.now().minusYears(5));
        Stay stayNew = RandomStay.createRandomStayWithoutIdGivenDiveCenter(diveCenter1);
        stayNew.setCheckInDate(LocalDate.now().minusDays(10));
        stayNew.setArriveDate(LocalDate.now().minusDays(10));

        Guest guestCheckedInTrue = RandomGuest.createRandomGuestWithoutIdGivenDiveCenter(diveCenter1);
        guestCheckedInTrue.setCheckedin(true);
        guestCheckedInTrue.setStays(ImmutableList.of(stayOld, stayNew));

        Guest guestCheckedInFalse = RandomGuest.createRandomGuestWithoutIdGivenDiveCenter(diveCenter1);
        guestCheckedInFalse.setCheckedin(false);

        Guest guestCheckedInFalse2 = RandomGuest.createRandomGuestWithoutIdGivenDiveCenter(diveCenter1);
        guestCheckedInFalse2.setCheckedin(false);

        guestRule.persist(ImmutableList.of(guestCheckedInTrue, guestCheckedInFalse, guestCheckedInFalse2));

        // when
        List<Guest> guestsCheckedInTrue = guestRepo.findByCheckedin(true);

        // then
        Guest actualGuestCheckedInTrue = Iterables.getOnlyElement(guestsCheckedInTrue);
        assertGuest(actualGuestCheckedInTrue, guestCheckedInTrue, ImmutableList.of(stayNew.getId(), stayOld.getId()));

        // when
        List<Guest> actualGuestsCheckedInFalse = guestRepo.findByCheckedin(false);

        // then
        assertThat(actualGuestsCheckedInFalse).hasSize(2);
    }

    @Test
    public void findByCheckedinAndDiveCenterId_shall_return_the_guests_of_divecenter_by_checkin() {
        // given

        Stay stayOld = RandomStay.createRandomStayWithoutIdGivenDiveCenter(diveCenter1);
        stayOld.setCheckInDate(LocalDate.now().minusYears(5));
        stayOld.setArriveDate(LocalDate.now().minusYears(5));
        Stay stayNew = RandomStay.createRandomStayWithoutIdGivenDiveCenter(diveCenter1);
        stayNew.setCheckInDate(LocalDate.now().minusDays(10));
        stayNew.setArriveDate(LocalDate.now().minusDays(10));

        Guest guestCheckedInTrue = RandomGuest.createRandomGuestWithoutIdGivenDiveCenter(diveCenter1);
        guestCheckedInTrue.setCheckedin(true);
        guestCheckedInTrue.setStays(ImmutableList.of(stayOld, stayNew));

        Guest guestCheckedInFalse = RandomGuest.createRandomGuestWithoutIdGivenDiveCenter(diveCenter1);
        guestCheckedInFalse.setCheckedin(false);

        Guest guestCheckedInFalse2 = RandomGuest.createRandomGuestWithoutIdGivenDiveCenter(diveCenter1);
        guestCheckedInFalse2.setCheckedin(false);

        guestRule.persist(ImmutableList.of(guestCheckedInTrue, guestCheckedInFalse, guestCheckedInFalse2));

        // when
        List<Guest> guestsCheckedInTrue = guestRepo.findByCheckedinAndDiveCenterId(true, diveCenter1.getId());

        // then
        Guest actualGuestCheckedInTrue = Iterables.getOnlyElement(guestsCheckedInTrue);
        assertGuest(actualGuestCheckedInTrue, guestCheckedInTrue, ImmutableList.of(stayNew.getId(), stayOld.getId()));

        // when
        List<Guest> actualGuestsCheckedInFalse = guestRepo.findByCheckedinAndDiveCenterId(false, diveCenter1.getId());

        // then
        assertThat(actualGuestsCheckedInFalse).hasSize(2);

        // when
        List<Guest> checkedInGuestsFromDivingCenter2 = guestRepo.findByCheckedinAndDiveCenterId(true, diveCenter2.getId());

        // then
        assertThat(checkedInGuestsFromDivingCenter2).hasSize(0);

        // when
        List<Guest> notCheckedInGuestsFromDivingCenter2 = guestRepo.findByCheckedinAndDiveCenterId(false, diveCenter2.getId());

        // then
        assertThat(notCheckedInGuestsFromDivingCenter2).hasSize(0);
    }

    @Test
    public void findByDiveCenterId_shall_return_the_guests_given_divecenter() {

        Stay stayOld = RandomStay.createRandomStayWithoutIdGivenDiveCenter(diveCenter1);
        stayOld.setCheckInDate(LocalDate.now().minusYears(5));
        stayOld.setArriveDate(LocalDate.now().minusYears(5));
        Stay stayNew = RandomStay.createRandomStayWithoutIdGivenDiveCenter(diveCenter1);
        stayNew.setCheckInDate(LocalDate.now().minusDays(10));
        stayNew.setArriveDate(LocalDate.now().minusDays(10));

        //given
        Guest guest1DivingCenter1 = RandomGuest.createRandomGuestWithoutIdGivenDiveCenter(diveCenter1);
        guest1DivingCenter1.setStays(ImmutableList.of(stayOld, stayNew));
        Guest guest2DivingCenter1 = RandomGuest.createRandomGuestWithoutIdGivenDiveCenter(diveCenter1);
        guestRule.persist(ImmutableList.of(guest1DivingCenter1, guest2DivingCenter1));

        // when
        List<Guest> guestsDivecenter1 = guestRepo.findByDiveCenterId(diveCenter1.getId());

        // then
        assertGuest(guestsDivecenter1.get(0), guest1DivingCenter1, ImmutableList.of(stayNew.getId(), stayOld.getId()));

        assertThat(guestsDivecenter1).hasSize(2);

        // when
        List<Guest> guestsDivecenter2 = guestRepo.findByDiveCenterId(diveCenter2.getId());

        // then
        assertThat(guestsDivecenter2).hasSize(0);
    }

    private void assertGuest(Guest actualGuest, Guest expectedGuest, List<Long> sortedStayIds) {
        assertThat(actualGuest.getId()).isEqualTo(expectedGuest.getId());
        assertThat(actualGuest.getFirstName()).isEqualTo(expectedGuest.getFirstName());
        assertThat(actualGuest.getLastName()).isEqualTo(expectedGuest.getLastName());
        assertThat(actualGuest.getBirthDate()).isEqualTo(expectedGuest.getBirthDate());
        assertThat(actualGuest.getCity()).isEqualTo(expectedGuest.getCity());
        assertThat(actualGuest.getCountry()).isEqualTo(expectedGuest.getCountry());
        assertThat(actualGuest.getEmail()).isEqualTo(expectedGuest.getEmail());
        assertThat(actualGuest.getNationality()).isEqualTo(expectedGuest.getNationality());
        assertThat(actualGuest.getPassportId()).isEqualTo(expectedGuest.getPassportId());
        assertThat(actualGuest.getPhone()).isEqualTo(expectedGuest.getPhone());
        assertThat(actualGuest.getPostcode()).isEqualTo(expectedGuest.getPostcode());

        assertThat(actualGuest.getStays()).hasSize(expectedGuest.getStays().size());
        List<Long> actualStayIds = actualGuest.getStays().stream().map(Stay::getId).collect(Collectors.toList());
        assertThat(actualStayIds).containsExactlyElementsOf(sortedStayIds);

        assertThat(actualGuest.getDiveCenter().getId()).isEqualTo(expectedGuest.getDiveCenter().getId());
    }

    @Test
    public void findById_shall_return_the_guest() {
        // given
        Guest randomGuest = RandomGuest.createRandomGuestWithoutIdGivenDiveCenter(diveCenter1);
        guestRule.persist(ImmutableList.of(randomGuest));

        //when
        Optional<Guest> expectedGuest = guestRepo.findById(randomGuest.getId());

        //then
        assertThat(expectedGuest.isPresent()).isTrue();
        assertGuest(randomGuest, expectedGuest.get(), ImmutableList.of(Iterables.getOnlyElement(randomGuest.getStays()).getId()));
    }

    @Test
    public void save_shall_update_the_guest() {
        // given
        Guest randomGuest = RandomGuest.createRandomGuestWithoutIdGivenDiveCenter(diveCenter1);
        guestRule.persist(ImmutableList.of(randomGuest));

        // when
        randomGuest.setFirstName(randomGuest.getFirstName() + "Update");
        randomGuest.setBirthDate(randomGuest.getBirthDate().minusYears(5));
        guestRepo.save(randomGuest);

        // then
        Optional<Guest> actualGuest = guestRepo.findById(randomGuest.getId());
        assertThat(actualGuest).isPresent();
        assertGuest(actualGuest.get(), randomGuest, ImmutableList.of(Iterables.getOnlyElement(randomGuest.getStays()).getId()));
    }

    @Test
    public void save_shall_create_the_guest() {
        // given
        Guest randomGuest = RandomGuest.createRandomGuestWithoutIdGivenDiveCenter(diveCenter1);

        //when
        guestRepo.save(randomGuest);

        //then
        Optional<Guest> actualGuest = guestRepo.findById(randomGuest.getId());
        assertThat(actualGuest).isPresent();
        assertGuest(actualGuest.get(), randomGuest, ImmutableList.of(Iterables.getOnlyElement(randomGuest.getStays()).getId()));
    }
}

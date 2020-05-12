package com.tabit.dcm2.repository;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.*;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static org.assertj.core.api.Assertions.assertThat;

public class StayRepoDbTest extends AbstractDbTest {
    @Autowired
    private IStayRepo stayRepo;

    private Stay activeStay;
    private Stay activeStay2;
    private Stay notActiveStay;
    private DiveCenter diveCenter;

    @Before
    public void setUp() {
        // given
        diveCenter = RandomDiveCenter.createRandomDiveCenterWithoutId();
        diveCenterRule.persist(ImmutableList.of(diveCenter));

        // given
        Guest guestForStay = RandomGuest.createRandomGuestWithoutIdGivenDiveCenter(diveCenter);
        activeStay = RandomStay.createRandomStayWithoutIdGivenActiveStateAndDiveCenter(true, diveCenter);
        guestForStay.setStays(ImmutableList.of(activeStay));
        activeStay.setLoans(
                ImmutableList.of(
                        RandomLoan.createRandomLoanWithoutIdGivenDiveCenter(diveCenter),
                        RandomLoan.createRandomLoanWithoutIdGivenDiveCenter(diveCenter)
                )
        );

        Guest guestForStay2 = RandomGuest.createRandomGuestWithoutIdGivenDiveCenter(diveCenter);
        activeStay2 = RandomStay.createRandomStayWithoutIdGivenActiveStateAndDiveCenter(true, diveCenter);
        activeStay2.setDiveCenter(diveCenter);
        notActiveStay = RandomStay.createRandomStayWithoutIdGivenActiveStateAndDiveCenter(false, diveCenter);
        notActiveStay.setDiveCenter(diveCenter);
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
    public void save_shall_update_the_stay() {
        // given
        activeStay.setFirstName(activeStay.getFirstName() + "Update");
        activeStay.setBirthDate(activeStay.getBirthDate().minusYears(5));
        activeStay.setHotel(activeStay.getHotel() + "Update");

        // when
        stayRepo.save(activeStay);

        // then
        Stay actualStay = stayRepo.findById(activeStay.getId()).get();
        assertStay(actualStay, activeStay);
    }

    @Test
    public void save_shall_create_the_stay() {
        // given
        Stay stay = RandomStay.createRandomStayWithoutId();
        stay.setLoans(new ArrayList<>());
        stay.setDiveCenter(diveCenter);

        Guest guest = RandomGuest.createRandomGuestWithoutId();
        guest.setDiveCenter(diveCenter);
        guest.setStays(ImmutableList.of(stay));

        guestRule.persist(ImmutableList.of(guest));

        // when
        stayRepo.save(stay);

        // then
        Optional<Stay> actualStay = stayRepo.findById(stay.getId());

        assertThat(actualStay).isPresent();
        assertStay(actualStay.get(), stay);
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

        List<Loan> expectedSortLoansByDateOut = sortLoansByDateOut(expectedStay);
        assertLoans(actualStay.getLoans(), expectedSortLoansByDateOut);

        assertThat(actualStay.getGuest().getId()).isEqualTo(expectedStay.getGuest().getId());

        assertThat(actualStay.getDiveCenter().getId()).isEqualTo(expectedStay.getDiveCenter().getId());
    }

    private List<Loan> sortLoansByDateOut(Stay stay) {
        List<Loan> sortedLoans = Lists.newArrayList(stay.getLoans());
        sortedLoans.sort(comparing(Loan::getDateOut));
        return sortedLoans;
    }

    private void assertLoans(List<Loan> actualLoans, List<Loan> expectedSortedLoans) {
        assertThat(actualLoans).hasSize(expectedSortedLoans.size());
        for (int i = 0; i < actualLoans.size(); i++) {
            assertLoan(actualLoans.get(i), expectedSortedLoans.get(i));
        }
    }

    private void assertLoan(Loan actualLoan, Loan expectedLoan) {
        assertThat(actualLoan.getId()).isEqualTo(expectedLoan.getId());
        assertThat(actualLoan.getDiveCenter().getId()).isEqualTo(expectedLoan.getDiveCenter().getId());
        assertThat(actualLoan.getDateOut()).isEqualTo(expectedLoan.getDateOut());
        assertThat(actualLoan.getDateReturn()).isEqualTo(expectedLoan.getDateReturn());
        assertThat(actualLoan.getPrice()).isEqualTo(expectedLoan.getPrice());
        assertThat(actualLoan.getEquipment().getId()).isEqualTo(expectedLoan.getEquipment().getId());
    }
}

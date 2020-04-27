package com.tabit.dcm2.repository;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.RandomGuest;
import com.tabit.dcm2.entity.RandomStay;
import com.tabit.dcm2.entity.Stay;
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

    @Test
    public void findByCheckedIn_shall_return_the_guests() {
        // given
        Stay stayOld = RandomStay.createRandomStayWithoutId();
        stayOld.setCheckInDate(LocalDate.now().minusYears(5));
        stayOld.setArriveDate(LocalDate.now().minusYears(5));
        Stay stayNew = RandomStay.createRandomStayWithoutId();
        stayNew.setCheckInDate(LocalDate.now().minusDays(10));
        stayNew.setArriveDate(LocalDate.now().minusDays(10));

        Guest guestCheckedInTrue = RandomGuest.createRandomGuestWitoutId();
        guestCheckedInTrue.setCheckedin(true);
        guestCheckedInTrue.setStays(ImmutableList.of(stayOld, stayNew));

        Guest guestCheckedInFalse = RandomGuest.createRandomGuestWitoutId();
        guestCheckedInFalse.setCheckedin(false);

        Guest guestCheckedInFalse2 = RandomGuest.createRandomGuestWitoutId();
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
    }

    @Test
    public void findById_shall_return_the_guest() {
        // given
        Guest randomGuest = RandomGuest.createRandomGuestWitoutId();
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
        Guest randomGuest = RandomGuest.createRandomGuestWitoutId();
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
        Guest randomGuest = RandomGuest.createRandomGuestWitoutId();

        //when
        guestRepo.save(randomGuest);

        //then
        Optional<Guest> actualGuest = guestRepo.findById(randomGuest.getId());
        assertThat(actualGuest).isPresent();
        assertGuest(actualGuest.get(), randomGuest, ImmutableList.of(Iterables.getOnlyElement(randomGuest.getStays()).getId()));
    }
}

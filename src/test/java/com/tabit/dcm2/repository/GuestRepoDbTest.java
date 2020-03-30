package com.tabit.dcm2.repository;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.RandomGuest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GuestRepoDbTest extends AbstractRepoDbTest {
    @Autowired
    private IGuestRepo guestRepo;

    @Test
    public void findByCheckedIn_shall_return_the_guests() {
        // given
        Guest guestCheckedInTrue = RandomGuest.createRandomGuestWitoutId();
        guestCheckedInTrue.setCheckedin(true);
        Guest guestCheckedInFalse = RandomGuest.createRandomGuestWitoutId();
        guestCheckedInFalse.setCheckedin(false);
        Guest guestCheckedInFalse2 = RandomGuest.createRandomGuestWitoutId();
        guestCheckedInFalse2.setCheckedin(false);

        guestRule.persist(ImmutableList.of(guestCheckedInTrue, guestCheckedInFalse, guestCheckedInFalse2));

        // when
        List<Guest> guestsCheckedInTrue = guestRepo.findByCheckedin(true);

        // then
        Guest actualGuestCheckedInTrue = Iterables.getOnlyElement(guestsCheckedInTrue);
        assertThat(actualGuestCheckedInTrue.getId()).isEqualTo(guestCheckedInTrue.getId());

        // when
        List<Guest> actualGuestsCheckedInFalse = guestRepo.findByCheckedin(false);

        // then
        assertThat(actualGuestsCheckedInFalse).hasSize(2);
    }
}

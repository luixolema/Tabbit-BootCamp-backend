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
    public void findByCheckedin_shall_return_the_guests() {
        // given
        Guest guestCheckedinTrue = RandomGuest.createRandomGuestWitoutId();
        guestCheckedinTrue.setCheckedin(true);
        Guest guestCheckedinFalse = RandomGuest.createRandomGuestWitoutId();
        guestCheckedinFalse.setCheckedin(false);
        Guest guestCheckedinFalse2 = RandomGuest.createRandomGuestWitoutId();
        guestCheckedinFalse2.setCheckedin(false);

        guestRule.persist(ImmutableList.of(guestCheckedinTrue, guestCheckedinFalse, guestCheckedinFalse2));

        // when
        List<Guest> guestsCheckinTrue = guestRepo.findByCheckedin(true);

        // then
        Guest actualGuestCheckinTrue = Iterables.getOnlyElement(guestsCheckinTrue);
        assertThat(actualGuestCheckinTrue.getId()).isEqualTo(guestCheckedinTrue.getId());

        // when
        List<Guest> actualGuestsCheckinFalse = guestRepo.findByCheckedin(false);

        // then
        assertThat(actualGuestsCheckinFalse).hasSize(2);
    }
}

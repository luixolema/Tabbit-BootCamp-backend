package com.tabit.dcm2.repository;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.RandomGuest;
import com.tabit.dcm2.entity.RandomStay;
import com.tabit.dcm2.entity.Stay;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StayRepoDbTest extends AbstractRepoDbTest {

    @Autowired
    private IStayRepo stayRepo;

    @Test
    public void findByGuest_shall_return_stays_for_guest() {
        // given
        Guest guest = RandomGuest.createRandomGuestWitoutId();
        guest.setCheckedin(true);

        Stay stayOld = RandomStay.createRandomStay();
        stayOld.setCheckInDate(LocalDate.of(2019, 4, 14)); // old one stay
        Stay stayNew = RandomStay.createRandomStay();
        stayNew.setCheckInDate(LocalDate.of(2020, 1, 25)); // new one stay

        guest.addStays(ImmutableList.of(stayOld, stayNew));
        guestRule.persist(ImmutableList.of(guest));

        // when
        List<Stay> guestStays = stayRepo.findByGuestOrderByCheckInDateDesc(guest);

        // then
        assertThat(guestStays).hasSize(2);
        Stay actualGuestStay = guestStays.get(0);
        assertThat(actualGuestStay.getGuest().getId()).isEqualTo(guest.getId());
        //check sorting by checkInDate
        assertThat(actualGuestStay.getCheckInDate()).isEqualTo(stayNew.getCheckInDate());
    }
}

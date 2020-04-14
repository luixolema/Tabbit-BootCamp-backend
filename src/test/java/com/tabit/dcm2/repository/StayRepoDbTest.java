package com.tabit.dcm2.repository;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.RandomGuest;
import com.tabit.dcm2.entity.RandomStay;
import com.tabit.dcm2.entity.Stay;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class StayRepoDbTest extends AbstractDbTest {
    @Autowired
    private IStayRepo stayRepo;

    private Stay stay;

    @Before
    public void setUp() {
        // given
        Guest guestForStay = RandomGuest.createRandomGuestWitoutId();
        stay = RandomStay.createRandomStayWithoutId();
        stay.setGuest(guestForStay);

        guestRule.persist(ImmutableList.of(guestForStay));
    }

    @Test
    public void findById_shall_return_the_stay() {
        // given
        stayRule.persist(ImmutableList.of(stay));

        //when
        Optional<Stay> expectedStay = stayRepo.findById(stay.getId());

        //then
        assertThat(expectedStay.isPresent()).isTrue();
        assertThat(stay).isEqualTo(expectedStay.get());
    }

    @Test
    public void save_shall_save_the_stay() {

        //when
        stayRepo.save(stay);

        //then
        Optional<Stay> expectedStay = stayRepo.findById(stay.getId());
        assertThat(expectedStay.isPresent()).isTrue();
        assertThat(stay).isEqualTo(expectedStay.get());
    }
}

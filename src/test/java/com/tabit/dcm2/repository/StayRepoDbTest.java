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
        guestForStay.addStays(ImmutableList.of(activeStay));

        Guest guestForStay2 = RandomGuest.createRandomGuestWitoutId();
        activeStay2 = RandomStay.createRandomStayWithoutIdGivenActiveState(true);
        notActiveStay = RandomStay.createRandomStayWithoutIdGivenActiveState(false);
        guestForStay2.addStays(ImmutableList.of(activeStay2, notActiveStay));

        guestRule.persist(ImmutableList.of(guestForStay));
        guestRule.persist(ImmutableList.of(guestForStay2));
    }

    @Test
    public void findById_shall_return_the_stay() {
        // given
        stayRule.persist(ImmutableList.of(activeStay));

        //when
        Optional<Stay> expectedStay = stayRepo.findById(activeStay.getId());

        //then
        assertThat(expectedStay.isPresent()).isTrue();
        assertThat(activeStay).isEqualTo(expectedStay.get());
    }

    @Test
    public void save_shall_save_the_stay() {

        //when
        stayRepo.save(activeStay);

        //then
        Optional<Stay> expectedStay = stayRepo.findById(activeStay.getId());
        assertThat(expectedStay.isPresent()).isTrue();
        assertThat(activeStay).isEqualTo(expectedStay.get());
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
}

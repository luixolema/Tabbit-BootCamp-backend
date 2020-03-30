package com.tabit.dcm2.repository;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.RandomStay;
import com.tabit.dcm2.entity.Stay;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class StayRepoDbTest extends AbstractRepoDbTest {
    @Autowired
    private IStayRepo stayRepo;

    @Test
    public void findByAll_shall_return_the_stay_by_id() {
        // TODO Leave me or delete me
        //given
        Stay randomStay1 = RandomStay.createRandomStay();
        Stay randomStay2 = RandomStay.createRandomStay();

        guestRule.persist(ImmutableList.of(randomStay1.getGuest(), randomStay2.getGuest()));
        stayRule.persist(ImmutableList.of(randomStay1, randomStay2));

        //when
        Optional<Stay> actualStay1 = stayRepo.findById(randomStay1.getId());
        Optional<Stay> actualStay2 = stayRepo.findById(randomStay2.getId());

        //then
        assertThat(actualStay1.isPresent()).isTrue();
        assertThat(actualStay2.isPresent()).isTrue();
    }
}
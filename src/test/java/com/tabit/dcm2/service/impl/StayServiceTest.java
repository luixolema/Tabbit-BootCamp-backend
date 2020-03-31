package com.tabit.dcm2.service.impl;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.RandomStay;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.exception.ResourceNotFoundException;
import com.tabit.dcm2.repository.IStayRepo;
import com.tabit.dcm2.testutils.ValueProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StayServiceTest {
    @Mock
    private IStayRepo stayRepo;

    @InjectMocks
    private StayService stayService;

    private ValueProvider valueProvider = new ValueProvider();

    @Test
    public void findById_shall_return_stay_by_id() {
        // given
        Stay randomStay = RandomStay.createRandomStay();
        when(stayRepo.findById(randomStay.getId())).thenReturn(Optional.of(randomStay));


        // when
        Stay actualStay = stayService.findById(randomStay.getId());

        // then
        assertThat(actualStay.getFirstName()).isEqualTo(randomStay.getFirstName());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findById_shall_throw_exception() {
        // given
        Long randomId = valueProvider.randomId();
        when(stayRepo.findById(randomId)).thenThrow(ResourceNotFoundException.class);

        // when
        Stay actualStay = stayService.findById(randomId);
    }

    @Test
    public void findByGuestIdOrderByCheckInDateDesc_shall_return_ordered_list_of_stays() {
        // given
        Long randomId = valueProvider.randomId();
        Stay randomStay1 = RandomStay.createRandomStay();
        randomStay1.setCheckInDate(LocalDate.of(2019, 4, 14));
        Stay randomStay2 = RandomStay.createRandomStay();
        randomStay2.setCheckInDate(LocalDate.of(2020, 1, 25));
        when(stayRepo.findByGuestIdOrderByCheckInDateDesc(randomId)).thenReturn(ImmutableList.of(randomStay2, randomStay1));

        // when
        List<Stay> stays = stayService.findByGuestIdOrderByCheckInDateDesc(randomId);

        // then
        assertThat(stays).containsExactly(randomStay2, randomStay1);
    }
}

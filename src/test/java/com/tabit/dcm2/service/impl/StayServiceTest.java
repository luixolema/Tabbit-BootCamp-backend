package com.tabit.dcm2.service.impl;

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
        assertThat(actualStay).isEqualTo(randomStay);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findById_shall_throw_exception() {
        // given
        Long randomId = valueProvider.randomId();
        when(stayRepo.findById(randomId)).thenThrow(ResourceNotFoundException.class);

        // when
        stayService.findById(randomId);
    }
}

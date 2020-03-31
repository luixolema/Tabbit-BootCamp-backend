package com.tabit.dcm2.controller;

import com.tabit.dcm2.entity.RandomStay;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.service.dto.StayDto;
import com.tabit.dcm2.service.impl.StayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StayControllerTest {
    @Mock
    private StayService stayService;

    @InjectMocks
    private StayController stayController;

    @Test
    public void getStay_shall_return_stay() {
        // given
        Stay randomStay = RandomStay.createRandomStay();
        when(stayService.findById(randomStay.getId())).thenReturn(randomStay);

        // when
        StayDto stayDto = stayController.getStay(randomStay.getId());

        // then
        assertThat(stayDto).isNotNull();
        assertThat(stayDto.getId()).isEqualTo(randomStay.getId());
    }
}

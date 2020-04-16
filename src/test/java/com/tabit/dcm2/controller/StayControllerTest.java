package com.tabit.dcm2.controller;

import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.RandomGuest;
import com.tabit.dcm2.entity.RandomStay;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.service.dto.RandomStayDto;
import com.tabit.dcm2.service.dto.StayDto;
import com.tabit.dcm2.service.impl.StayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.tabit.dcm2.testutils.StayMappingAssertions.assertStayDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
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
        Guest guest = RandomGuest.createRandomGuest();
        Stay randomStay = RandomStay.createRandomStay();
        randomStay.setGuest(guest);
        when(stayService.findById(randomStay.getId())).thenReturn(randomStay);

        // when
        StayDto stayDto = stayController.getStay(randomStay.getId());

        // then
        assertStayDto(stayDto, randomStay);
    }

    @Test
    public void updateStay_shall_update_stay() {
        // given
        StayDto randomStayDto = RandomStayDto.createRandomStayDto();

        // when
        stayController.updateStay(randomStayDto);

        // then
        verify(stayService).updateStay(randomStayDto);
    }

    @Test
    public void isBoxFree_shall_return_the_right_value() {
        // given
        Stay activeStay = RandomStay.createRandomStayWithoutIdGivenActiveState(true);
        when(stayService.isBoxFree(activeStay.getBoxNumber())).thenReturn(false);
        when(stayService.isBoxFree(not(eq(activeStay.getBoxNumber())))).thenReturn(true);

        // when
        boolean resultFalse = stayService.isBoxFree(activeStay.getBoxNumber());
        boolean resultTrue = stayService.isBoxFree(activeStay.getBoxNumber() + "Update");

        // then
        assertThat(resultFalse).isFalse();
        assertThat(resultTrue).isTrue();
    }

    @Test
    public void addActiveStay_shall_add_new_stay() {
        // given
        StayDto randomStayDto = RandomStayDto.createRandomStayDto();

        // when
        stayController.addActiveStay(randomStayDto);

        // then
        verify(stayService).addActiveStay(randomStayDto);
    }
}

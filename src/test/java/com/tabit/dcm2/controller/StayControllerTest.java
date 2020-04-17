package com.tabit.dcm2.controller;

import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.RandomGuest;
import com.tabit.dcm2.entity.RandomStay;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.service.dto.RandomStayDto;
import com.tabit.dcm2.service.dto.StayDto;
import com.tabit.dcm2.service.impl.StayService;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.tabit.dcm2.testutils.StayMappingAssertions.assertStayDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(Theories.class)
public class StayControllerTest {

    @Mock
    private StayService stayService;

    @InjectMocks
    private StayController stayController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

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

    @DataPoints("isBoxFree")
    public static Boolean[] isBoxFree = new Boolean[]{true, false};

    @Theory
    @Test
    public void isBoxFree_shall_return_the_right_value(@FromDataPoints("isBoxFree") boolean isBoxFree) {
        // given
        Stay activeStay = RandomStay.createRandomStay();
        when(stayService.isBoxFree(activeStay.getBoxNumber())).thenReturn(isBoxFree);

        // when
        boolean actualIsBoxFree = stayService.isBoxFree(activeStay.getBoxNumber());

        // then
        assertThat(actualIsBoxFree).isEqualTo(isBoxFree);
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

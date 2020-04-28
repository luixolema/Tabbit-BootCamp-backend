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
import org.junit.experimental.theories.Theories;
import org.junit.runner.RunWith;
import org.mockito.*;

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

    @Captor
    ArgumentCaptor<StayDto> stayDtoArgumentCaptor;

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
        verify(stayService).updateStay(stayDtoArgumentCaptor.capture());
        StayDto validatedStayDto = stayDtoArgumentCaptor.getValue();
        assertThat(validatedStayDto).isEqualTo(randomStayDto);
        assertThat(validatedStayDto).isNotSameAs(randomStayDto);
    }

}

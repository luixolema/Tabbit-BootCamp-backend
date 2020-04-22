package com.tabit.dcm2.service.impl;

import com.tabit.dcm2.entity.BoxManagement;
import com.tabit.dcm2.exception.BoxReservationException;
import com.tabit.dcm2.repository.IBoxManagementRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BoxManagementServiceTest {

    @Mock
    private IBoxManagementRepo boxManagementRepo;


    @InjectMocks
    private BoxManagementService boxManagementService;

    private BoxManagement boxManagement;

    @Before
    public void setUp() {
        // given
        boxManagement = new BoxManagement();
        boxManagement.setBoxNumber("test");
    }

    @Test
    public void isBoxEmpty_shall_return_the_right_value() {
        // given
        when(boxManagementRepo.findByBoxNumber(boxManagement.getBoxNumber())).thenReturn(Optional.of(boxManagement));
        when(boxManagementRepo.findByBoxNumber(not(eq(boxManagement.getBoxNumber())))).thenReturn(Optional.empty());

        // when
        boolean isBoxFree = boxManagementService.isBoxFree(boxManagement.getBoxNumber());

        // then
        assertThat(isBoxFree).isFalse();

        // when
        isBoxFree = boxManagementService.isBoxFree(boxManagement.getBoxNumber()+"otherBox");

        // then
        assertThat(isBoxFree).isTrue();
    }

    @Test(expected = BoxReservationException.class)
    public void reserveBox_shall_throw_an_exception_if_the_boxNumber_is_already_in_use() {
        // given
        when(boxManagementRepo.save(Mockito.any(BoxManagement.class))).thenThrow(DataIntegrityViolationException.class);

        // when
        boxManagementService.reserveBox(boxManagement.getBoxNumber());
    }

    @Test
    public void reserveBox_shall_save_a_new_boxNumber_if_its_not_in_use() {
        // when
        BoxManagement boxManagement = new BoxManagement();
        boxManagementService.reserveBox(boxManagement.getBoxNumber());

        //then
        verify(boxManagementRepo).save(Mockito.any(BoxManagement.class));
    }

}

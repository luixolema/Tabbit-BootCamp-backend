package com.tabit.dcm2.service.impl;

import com.tabit.dcm2.entity.*;
import com.tabit.dcm2.exception.BoxReservationException;
import com.tabit.dcm2.repository.IBoxManagementRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BoxManagementServiceTest {

    @Mock
    private IBoxManagementRepo boxManagementRepo;
    @Mock
    private AuthenticationService authenticationService;
    @Captor
    private ArgumentCaptor<BoxManagement> boxManagementArgumentCaptor;
    @InjectMocks
    private BoxManagementService boxManagementService;

    private BoxManagement boxManagement;

    private DiveCenter diveCenter;

    @Before
    public void setUp() {
        // given
        boxManagement = RandomBoxManagement.createRandomBoxManagement();
        User user = RandomUser.createRandomUse();
        diveCenter = user.getDiveCenter();
        when(authenticationService.getLoggedInUser()).thenReturn(user);
    }

    @Test
    public void isBoxFree_shall_return_the_right_value() {
        // given
        when(boxManagementRepo.findByBoxNumberAndDiveCenterId(boxManagement.getBoxNumber(), diveCenter.getId())).thenReturn(Optional.of(boxManagement)).thenReturn(Optional.empty());

        // when
        boolean isBoxFree = boxManagementService.isBoxFree(boxManagement.getBoxNumber());

        // then
        assertThat(isBoxFree).isFalse();

        // when
        isBoxFree = boxManagementService.isBoxFree(boxManagement.getBoxNumber());

        // then
        assertThat(isBoxFree).isTrue();
    }

    @Test(expected = BoxReservationException.class)
    public void reserveBox_shall_throw_an_exception_if_the_boxNumber_is_already_in_use() {
        // given
        when(boxManagementRepo.save(any(BoxManagement.class))).thenThrow(DataIntegrityViolationException.class);

        // when
        boxManagementService.reserveBox(boxManagement.getBoxNumber());
    }

    @Test
    public void reserveBox_shall_save_a_new_boxNumber_if_its_not_in_use() {
        // when
        boxManagementService.reserveBox(boxManagement.getBoxNumber());

        //then
        verify(boxManagementRepo).save(boxManagementArgumentCaptor.capture());
        BoxManagement actual = boxManagementArgumentCaptor.getValue();
        assertThat(actual.getBoxNumber()).isEqualTo(boxManagement.getBoxNumber());
    }

    @Test
    public void releaseBox_shall_delete_box() {
        // given
        when(boxManagementRepo.findByBoxNumber(boxManagement.getBoxNumber())).thenReturn(Optional.of(boxManagement));

        // when
        boxManagementService.releaseBox(boxManagement.getBoxNumber());

        //then
        verify(boxManagementRepo).delete(boxManagement);
    }

    @Test
    public void releaseBox_shall_not_delete_non_existing_box() {
        // given
        when(boxManagementRepo.findByBoxNumber(boxManagement.getBoxNumber())).thenReturn(Optional.empty());

        // when
        boxManagementService.releaseBox(boxManagement.getBoxNumber());

        //then
        verify(boxManagementRepo, never()).delete(any(BoxManagement.class));
    }
}

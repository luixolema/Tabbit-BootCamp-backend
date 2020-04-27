package com.tabit.dcm2.controller;

import com.tabit.dcm2.entity.BoxManagement;
import com.tabit.dcm2.entity.RandomBoxManagement;
import com.tabit.dcm2.service.impl.BoxManagementService;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(Theories.class)
public class BoxManagementControllerTest {

    @Mock
    private BoxManagementService boxManagementService;

    @InjectMocks
    private BoxManagementController boxManagementController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @DataPoints("isBoxFree")
    public static Boolean[] isBoxFree = new Boolean[]{true, false};

    @Theory
    @Test
    public void isBoxFree_shall_return_the_right_value(@FromDataPoints("isBoxFree") boolean isBoxFree) {
        // given
        BoxManagement boxManagement = RandomBoxManagement.createRandomBoxManagement();
        ;
        when(boxManagementService.isBoxFree(boxManagement.getBoxNumber())).thenReturn(isBoxFree);

        // when
        boolean actualIsBoxFree = boxManagementController.isBoxFree(boxManagement.getBoxNumber());

        // then
        assertThat(actualIsBoxFree).isEqualTo(isBoxFree);
    }


}

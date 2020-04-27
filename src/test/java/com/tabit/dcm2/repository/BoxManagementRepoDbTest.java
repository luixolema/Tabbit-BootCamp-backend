package com.tabit.dcm2.repository;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.BoxManagement;
import com.tabit.dcm2.entity.RandomBoxManagement;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class BoxManagementRepoDbTest extends AbstractDbTest {
    @Autowired
    private IBoxManagementRepo boxManagementRepo;

    private BoxManagement boxManagement;

    @Before
    public void setUp() {
        // given
        boxManagement = RandomBoxManagement.createRandomBoxManagement();
        boxManagementRule.persist(ImmutableList.of(boxManagement));
    }

    @Test
    public void delete_shall_remove_the_box_management() {
        // given
        Optional<BoxManagement> expectedBoxManagement = boxManagementRepo.findByBoxNumber(boxManagement.getBoxNumber());

        //when
        boxManagementRepo.delete(expectedBoxManagement.get());

        //then
        expectedBoxManagement = boxManagementRepo.findByBoxNumber(boxManagement.getBoxNumber());
        assertThat(expectedBoxManagement).isEmpty();
    }

    @Test
    public void findByBoxNumber_shall_return_the_box_management() {
        //when
        Optional<BoxManagement> expectedBoxManagement = boxManagementRepo.findByBoxNumber(boxManagement.getBoxNumber());

        //then
        assertThat(expectedBoxManagement.isPresent()).isTrue();
        assertBoxManagement(boxManagement, expectedBoxManagement.get());
    }

    @Test
    public void save_shall_create_the_box_management() {
        // when
        BoxManagement boxManagement = RandomBoxManagement.createRandomBoxManagement();
        boxManagementRepo.save(boxManagement);

        // then
        Optional<BoxManagement> expectedBoxManagement = boxManagementRepo.findById(boxManagement.getId());

        assertThat(expectedBoxManagement.isPresent()).isTrue();
        assertBoxManagement(expectedBoxManagement.get(), boxManagement);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void save_shall_fail_on_duplication_of_box_number() {
        // given
        BoxManagement duplicatedBoxManagement = RandomBoxManagement.createRandomBoxManagement();
        duplicatedBoxManagement.setBoxNumber(boxManagement.getBoxNumber());

        // when
        boxManagementRepo.save(duplicatedBoxManagement);
    }

    private void assertBoxManagement(BoxManagement actualBoxManagement, BoxManagement expectedBoxManagement) {
        assertThat(actualBoxManagement.getId()).isEqualTo(expectedBoxManagement.getId());
        assertThat(actualBoxManagement.getBoxNumber()).isEqualTo(expectedBoxManagement.getBoxNumber());
    }

}

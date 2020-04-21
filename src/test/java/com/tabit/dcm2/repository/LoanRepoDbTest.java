package com.tabit.dcm2.repository;

import com.tabit.dcm2.entity.*;
import com.tabit.dcm2.testutils.LoanMappingAssertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class LoanRepoDbTest extends AbstractDbTest {
    @Autowired
    private ILoanRepo loanRepo;

    private Loan loan1;
    private Loan loan2;
    private Equipment equipment;
    private Stay stay;

    @Before
    public void setUp() {
        //given
        EquipmentType equipmentType = RandomEquipmentType.createRandomEquipmentTypeWithoutId();
        equipmentTypeRule.persist(Collections.singletonList(equipmentType));

        equipment = RandomEquipment.createRandomEquipmentWithoutId();
        equipment.setEquipmentType(equipmentType);
        equipmentRule.persist(Collections.singletonList(equipment));

        Guest guest = RandomGuest.createRandomGuestWitoutId();
        guestRule.persist(Collections.singletonList(guest));
        stay = guest.getStays().get(0);

        loan1 = RandomLoan.createRandomLoanWithoutId();
        loan1.setEquipment(equipment);
        loan1.setStay(stay);
        loanRule.persist(Collections.singletonList(loan1));
    }

    @Test
    public void findById_shall_return_the_loan() {
        //when
        Optional<Loan> actualLoan = loanRepo.findById(loan1.getId());

        //then
        assertThat(actualLoan.isPresent()).isTrue();
        LoanMappingAssertions.assertLoan(actualLoan.get(), loan1);
    }

    @Test
    public void save_shall_save_the_loan() {
        //given
        loan2 = RandomLoan.createRandomLoanWithoutId();
        loan2.setEquipment(equipment);
        loan2.setStay(stay);

        //when
        loanRepo.save(loan2);

        //then
        Optional<Loan> actualLoan = loanRepo.findById(loan2.getId());

        assertThat(actualLoan.isPresent()).isTrue();
        LoanMappingAssertions.assertLoan(actualLoan.get(), loan2);
    }
}

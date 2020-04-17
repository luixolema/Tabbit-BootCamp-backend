package com.tabit.dcm2.repository;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.Loan;
import com.tabit.dcm2.entity.RandomGuest;
import com.tabit.dcm2.entity.RandomLoan;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class LoanRepoDbTest extends AbstractDbTest {
    @Autowired
    private ILoanRepo loanRepo;

    private Loan loan1;
    private Loan loan2;
    private Guest guest;

    @Before
    public void setUp() {
        //given
        guest = RandomGuest.createRandomGuestWitoutId();
        loan1 = guest.getStays().get(0).getLoans().get(0);

        guestRule.persist(ImmutableList.of(guest));
    }

    @Test
    public void findById_shall_return_the_loan() {
        //when
        Optional<Loan> actualLoan = loanRepo.findById(loan1.getId());

        //then
        assertThat(actualLoan.isPresent()).isTrue();
        assertLoan(actualLoan.get(), loan1);
    }

    @Test
    public void save_shall_save_the_loan() {
        //given
        loan2 = RandomLoan.createRandomLoanWithoutId();
        guest.getStays().get(0).addLoans(ImmutableList.of(loan2));

        //when
        loanRepo.save(loan2);

        //then
        Optional<Loan> actualLoan = loanRepo.findById(loan2.getId());

        assertThat(actualLoan.isPresent()).isTrue();
        assertLoan(actualLoan.get(), loan2);
    }

    private void assertLoan(Loan actualLoan, Loan expectedLoan) {
        assertThat(actualLoan.getId()).isEqualTo(expectedLoan.getId());
        assertThat(actualLoan.getDayOut()).isEqualTo(expectedLoan.getDayOut());
        assertThat(actualLoan.getDayReturn()).isEqualTo(expectedLoan.getDayReturn());
        assertThat(actualLoan.getEquipment().getId()).isEqualTo(expectedLoan.getEquipment().getId());
        assertThat(actualLoan.getPrice()).isEqualTo(expectedLoan.getPrice());
        assertThat(actualLoan.getStay().getId()).isEqualTo(expectedLoan.getStay().getId());
    }
}

package com.tabit.dcm2.entity;

import com.tabit.dcm2.testutils.ValueProvider;

public class RandomLoan {

    public static Loan createRandomLoan() {
        ValueProvider valueProvider = new ValueProvider();

        Loan loan = new Loan();
        loan.setId(valueProvider.randomId());
        loan.setDateOut(valueProvider.randomLocalDate());
        loan.setDateReturn(valueProvider.randomLocalDate());
        loan.setEquipment(RandomEquipment.createRandomEquipment());

        return loan;
    }

    public static Loan createRandomLoanWithoutId() {
        Loan randomLoan = createRandomLoan();
        randomLoan.setEquipment(null);
        randomLoan.setId(null);

        return randomLoan;
    }
}

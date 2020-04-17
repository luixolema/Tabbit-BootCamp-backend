package com.tabit.dcm2.entity;

import com.tabit.dcm2.testutils.ValueProvider;

public class RandomLoan {

    public static Loan createRandomLoanWithoutId() {
        Loan loan = createRandomLoan();
        loan.setId(null);
        loan.setEquipment(RandomEquipment.createRandomEquipmentWithoutId());
        return loan;
    }

    public static Loan createRandomLoan() {
        ValueProvider valueProvider = new ValueProvider();

        Loan loan = new Loan();
        loan.setId(valueProvider.randomId());
        loan.setEquipment(RandomEquipment.createRandomEquipment());
        loan.setDayOut(valueProvider.randomLocalDate());
        loan.setDayReturn(valueProvider.randomLocalDate());
        loan.setPrice(valueProvider.randomDouple());
        return loan;
    }
}

package com.tabit.dcm2.entity;

import com.tabit.dcm2.testutils.ValueProvider;

import java.time.LocalDate;

public class RandomLoan {

    public static Loan createRandomLoanWithoutId() {
        Loan loan = createRandomLoan();
        loan.setId(null);
        loan.setEquipment(RandomEquipment.createRandomEquipmentWithoutId());
        return loan;
    }

    public static Loan createRandomLoan() {
        ValueProvider valueProvider = new ValueProvider();
        LocalDate today = LocalDate.now();
        Integer randomValue = valueProvider.randomIntBetween(1, 10);
        LocalDate dateOut = today.minusDays(randomValue);
        LocalDate dateReturn = today.plusDays(randomValue);

        Loan loan = new Loan();
        loan.setId(valueProvider.randomId());
        loan.setEquipment(RandomEquipment.createRandomEquipment());
        loan.setDateOut(dateOut);
        if (valueProvider.randomBoolean()) {
            loan.setDateReturn(dateReturn);
        }
        loan.setPrice(valueProvider.randomDouple());
        return loan;
    }
}

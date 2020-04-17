package com.tabit.dcm2.entity;

public class LoanRule extends AbstractDbRule<Loan> {
    @Override
    protected String getEntitySimpleName() {
        return Loan.class.getSimpleName();
    }
}

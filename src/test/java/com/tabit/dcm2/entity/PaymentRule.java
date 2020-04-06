package com.tabit.dcm2.entity;

public class PaymentRule extends AbstractDbRule<Payment> {

    @Override
    protected String getEntitySimpleName() {
        return Payment.class.getSimpleName();
    }
}

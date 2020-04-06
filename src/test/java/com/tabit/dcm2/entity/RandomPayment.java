package com.tabit.dcm2.entity;

import com.tabit.dcm2.testutils.ValueProvider;

public class RandomPayment {
    public static Payment createRandomPaymentWithoutId() {
        Payment payment = createRandomPayment();
        payment.setId(null);
        return payment;
    }

    public static Payment createRandomPayment() {
        ValueProvider valueProvider = new ValueProvider();

        Payment payment = new Payment();
        payment.setId(valueProvider.randomId());
        payment.setActivityType(valueProvider.randomString("activity"));
        payment.setActivityDate(valueProvider.randomLocalDate());
        payment.setPrice(valueProvider.randomPrice());

        return payment;
    }
}

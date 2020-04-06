package com.tabit.dcm2.entity;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.testutils.ValueProvider;

public class RandomInvoice {
    public static Invoice createRandomInvoice(Stay stay) {
        ValueProvider valueProvider = new ValueProvider();

        Invoice invoice = new Invoice();
        invoice.setId(valueProvider.randomId());
        invoice.setInvoiceId(valueProvider.randomString("Invoice"));
        invoice.addPayments(ImmutableList.of(RandomPayment.createRandomPayment(), RandomPayment.createRandomPayment(), RandomPayment.createRandomPayment()));
        invoice.setStay(stay);
        return invoice;
    }

    public static Invoice createRandomGInvoiceWithoutId(Stay stay) {
        Invoice invoice = createRandomInvoice(stay);
        invoice.setId(null);
        return invoice;
    }
}

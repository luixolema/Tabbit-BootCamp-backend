package com.tabit.dcm2.entity;

public class InvoiceRule extends AbstractDbRule<Invoice> {

    @Override
    protected String getEntitySimpleName() {
        return Invoice.class.getSimpleName();
    }
}

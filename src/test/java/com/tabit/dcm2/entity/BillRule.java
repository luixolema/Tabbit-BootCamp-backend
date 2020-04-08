package com.tabit.dcm2.entity;

public class BillRule extends AbstractDbRule<Bill> {
    @Override
    protected String getEntitySimpleName() {
        return Bill.class.getSimpleName();
    }
}

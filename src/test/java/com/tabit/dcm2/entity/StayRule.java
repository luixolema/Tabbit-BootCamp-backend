package com.tabit.dcm2.entity;

public class StayRule extends AbstractDbRule<Stay> {
    @Override
    protected String getEntitySimpleName() {
        return Stay.class.getSimpleName();
    }
}

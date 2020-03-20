package com.tabit.dcm2.entity;

public class GuestRule extends AbstractDbRule<Guest> {

    @Override
    protected String getEntitySimpleName() {
        return Guest.class.getSimpleName();
    }
}

package com.tabit.dcm2.entity;

public class UserRule extends AbstractDbRule<User> {
    @Override
    protected String getEntitySimpleName() {
        return User.class.getSimpleName();
    }
}

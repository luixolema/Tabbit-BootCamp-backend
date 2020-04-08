package com.tabit.dcm2.entity;

import com.tabit.dcm2.testutils.ValueProvider;

public class RandomBill {

    public static Bill createRandomBillWithoutId() {
        Bill bill = createRandomBill();
        bill.setId(null);
        return bill;
    }

    public static Bill createRandomBill() {
        ValueProvider valueProvider = new ValueProvider();

        Bill bill = new Bill();
        bill.setId(valueProvider.randomId());
        bill.setCode(valueProvider.randomString("code"));

        return bill;
    }
}

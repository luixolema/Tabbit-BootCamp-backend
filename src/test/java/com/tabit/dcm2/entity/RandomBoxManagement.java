package com.tabit.dcm2.entity;

import com.tabit.dcm2.testutils.ValueProvider;

public class RandomBoxManagement {
    public static BoxManagement createRandomBoxManagement() {
        ValueProvider valueProvider = new ValueProvider();

        BoxManagement boxManagement = new BoxManagement();
        boxManagement.setBoxNumber(valueProvider.randomString("boxNumber"));
        return boxManagement;
    }
}

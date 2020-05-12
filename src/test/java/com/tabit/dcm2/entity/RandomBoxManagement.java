package com.tabit.dcm2.entity;

import com.tabit.dcm2.testutils.ValueProvider;

public class RandomBoxManagement {

    public static BoxManagement createRandomBoxManagement() {
        ValueProvider valueProvider = new ValueProvider();

        BoxManagement boxManagement = new BoxManagement();
        boxManagement.setId(valueProvider.randomId());
        boxManagement.setBoxNumber(valueProvider.randomString("boxNumber"));
        boxManagement.setDiveCenter(RandomDiveCenter.createRandomDiveCenter());

        return boxManagement;
    }

    public static BoxManagement createRandomBoxManagementWithoutId() {
        BoxManagement boxManagement = createRandomBoxManagement();
        boxManagement.setId(null);

        return boxManagement;
    }

    public static BoxManagement createRandomBoxManagementGivenDiveCenter(DiveCenter diveCenter) {
        BoxManagement boxManagement = createRandomBoxManagement();
        boxManagement.setDiveCenter(diveCenter);

        return boxManagement;
    }

    public static BoxManagement createRandomBoxManagementWithoutIdGivenDiveCenter(DiveCenter diveCenter) {
        BoxManagement boxManagement = createRandomBoxManagementGivenDiveCenter(diveCenter);
        boxManagement.setId(null);

        return boxManagement;
    }
}

package com.tabit.dcm2.entity;

import com.tabit.dcm2.testutils.ValueProvider;

public class RandomDiveCenter {

    public static DiveCenter createRandomDiveCenter() {
        ValueProvider valueProvider = new ValueProvider();

        DiveCenter diveCenter = new DiveCenter();
        diveCenter.setId(valueProvider.randomId());
        diveCenter.setName(valueProvider.randomString("name"));
        diveCenter.setLatitude(valueProvider.randomDouble());
        diveCenter.setLongitude(valueProvider.randomDouble());

        return diveCenter;
    }

    public static DiveCenter createRandomDiveCenterWithoutId() {
        DiveCenter diveCenter = createRandomDiveCenter();
        diveCenter.setId(null);

        return diveCenter;
    }
}

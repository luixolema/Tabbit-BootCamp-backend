package com.tabit.dcm2.entity;

import com.tabit.dcm2.testutils.ValueProvider;

public class RandomActivity {

    public static Activity createRandomActivityWithoutId() {
        Activity activity = createRandomActivity();
        activity.setId(null);
        return activity;
    }

    public static Activity createRandomActivity() {
        ValueProvider valueProvider = new ValueProvider();

        Activity activity = new Activity();
        activity.setId(valueProvider.randomId());
        activity.setDate(valueProvider.randomLocalDate());
        activity.setPos(valueProvider.randomString("pos"));
        activity.setPrice(valueProvider.randomDouble());
        activity.setType(Activity.ActivityType.TYPE_1);

        return activity;
    }
}

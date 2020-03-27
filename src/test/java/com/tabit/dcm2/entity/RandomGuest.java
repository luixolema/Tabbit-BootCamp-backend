package com.tabit.dcm2.entity;

import com.tabit.dcm2.testutils.ValueProvider;

public class RandomGuest {

    public static Guest createRandomGuest() {
        ValueProvider valueProvider = new ValueProvider();

        Guest guest = new Guest();
        guest.setId(valueProvider.randomId());
        guest.setFirstName(valueProvider.randomString("firstname"));
        guest.setLastName(valueProvider.randomString("lastname"));
        guest.setCheckedin(valueProvider.randomBoolean());

        return guest;
    }

    public static Guest createRandomGuestWitoutId() {
        Guest guest = createRandomGuest();
        guest.setId(null);

        return guest;
    }
}

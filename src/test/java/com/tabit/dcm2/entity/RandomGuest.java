package com.tabit.dcm2.entity;

import com.tabit.dcm2.testutils.ValueProvider;

public class RandomGuest {

    public static Guest createRandomGuest() {
        ValueProvider valueProvider = new ValueProvider();

        Guest guest = new Guest();
        guest.setId(valueProvider.randomId());
        guest.setFirstName(valueProvider.randomString("firstname"));
        guest.setLastName(valueProvider.randomString("lastname"));
        guest.setBirthDate(valueProvider.randomDate());
        guest.setCity(valueProvider.randomString("city"));
        guest.setCountry(valueProvider.randomString("country"));
        guest.setBrevet(valueProvider.randomString("brevet"));
        guest.setDivesAmount(valueProvider.randomInt());
        guest.setLastDive(valueProvider.randomDate());
        guest.setEmail(valueProvider.randomString("email"));
        guest.setNationality(valueProvider.randomString("nationality"));
        guest.setPassportId(valueProvider.randomString("passport"));
        guest.setPhone(valueProvider.randomString("phone"));
        guest.setMedicalStatement(valueProvider.randomBoolean());
        guest.setNitrox(valueProvider.randomBoolean());
        guest.setStreet(valueProvider.randomString("street"));
        guest.setPostcode(valueProvider.randomString("postcode"));
        guest.setCheckedin(valueProvider.randomBoolean());
        //guest.setBoxId(valueProvider.randomBoolean() ? valueProvider.randomId() : null);

        return guest;
    }

    public static Guest createRandomGuestWitoutId() {
        Guest guest = createRandomGuest();
        guest.setId(null);

        return guest;
    }
}

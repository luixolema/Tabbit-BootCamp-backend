package com.tabit.dcm2.entity;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.testutils.ValueProvider;

public class RandomGuest {

    public static Guest createRandomGuest() {
        ValueProvider valueProvider = new ValueProvider();

        Guest guest = new Guest();
        guest.setId(valueProvider.randomId());
        guest.setFirstName(valueProvider.randomString("firstname"));
        guest.setLastName(valueProvider.randomString("lastname"));
        guest.setBirthDate(valueProvider.randomLocalDate());
        guest.setCity(valueProvider.randomString("city"));
        guest.setCountry(valueProvider.randomString("country"));
        guest.setEmail(valueProvider.randomString("email"));
        guest.setNationality(valueProvider.randomString("nationality"));
        guest.setPassportId(valueProvider.randomString("passport"));
        guest.setPhone(valueProvider.randomString("phone"));
        guest.setStreet(valueProvider.randomString("street"));
        guest.setPostcode(valueProvider.randomString("postcode"));
        guest.setCheckedin(valueProvider.randomBoolean());
        guest.addStays(ImmutableList.of(RandomStay.createRandomStay()));

        return guest;
    }

    public static Guest createRandomGuestWitoutId() {
        Guest guest = createRandomGuest();
        guest.setId(null);
        guest.addStays(ImmutableList.of(RandomStay.createRandomStayWithoutId()));

        return guest;
    }

    public static Guest createSpecificGuestForStay(Stay stay) {
        Guest guest = new Guest();
        guest.setId(stay.getGuest().getId());
        guest.setFirstName(stay.getFirstName());
        guest.setLastName(stay.getLastName());
        guest.setBirthDate(stay.getBirthDate());
        guest.setCity(stay.getCity());
        guest.setCountry(stay.getCountry());
        guest.setEmail(stay.getEmail());
        guest.setNationality(stay.getNationality());
        guest.setPassportId(stay.getPassportId());
        guest.setPhone(stay.getPhone());
        guest.setStreet(stay.getStreet());
        guest.setPostcode(stay.getPostcode());
        guest.addStays(ImmutableList.of(stay));

        return guest;
    }
}

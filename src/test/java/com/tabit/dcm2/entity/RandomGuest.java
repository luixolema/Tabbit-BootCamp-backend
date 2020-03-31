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
        guest.addStays(ImmutableList.of(createStayForGuest(guest)));

        return guest;
    }

    public static Guest createRandomGuestWitoutId() {
        Guest guest = createRandomGuest();
        guest.setId(null);

        return guest;
    }

    private static Stay createStayForGuest(Guest guest) {
        Stay stay = RandomStay.createRandomStayWithoutId();
        stay.setGuest(guest);
        stay.setFirstName(guest.getFirstName());
        stay.setLastName(guest.getLastName());
        stay.setBirthDate(guest.getBirthDate());
        stay.setNationality(guest.getNationality());
        stay.setCountry(guest.getCountry());
        stay.setCity(guest.getCity());
        stay.setPostcode(guest.getPostcode());
        stay.setStreet(guest.getStreet());
        stay.setEmail(guest.getEmail());
        stay.setPhone(guest.getPhone());
        stay.setPassportId(guest.getPassportId());

        return stay;
    }
}

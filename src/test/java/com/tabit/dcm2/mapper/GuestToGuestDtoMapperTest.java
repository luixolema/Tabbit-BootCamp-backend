package com.tabit.dcm2.mapper;

import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.RandomGuest;
import com.tabit.dcm2.service.GuestDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GuestToGuestDtoMapperTest {

    private GuestToGuestDtoMapper mapper = new GuestToGuestDtoMapper();

    @Test
    public void map() {
        Guest guest = RandomGuest.createRandomGuest();

        GuestDto guestDto = mapper.map(guest);
        assertEquals(guestDto.getFirstName(), guest.getFirstName());
        assertEquals(guestDto.getLastName(), guest.getLastName());
        assertEquals(guestDto.getBoxId(), guest.getBox().getId());
        assertEquals(guestDto.isCheckedin(), guest.isCheckedin());
    }

    @Test
    public void testMap() {
        Guest guest1 = RandomGuest.createRandomGuest();
        Guest guest2 = RandomGuest.createRandomGuest();

        List<Guest> guests = new ArrayList<>();
        guests.add(guest1);
        guests.add(guest2);

        List<GuestDto> guestDtos = mapper.map(guests);
        assertEquals(guests.size(), guestDtos.size());
        assertEquals(guestDtos.get(0).getFirstName(), guest1.getFirstName());
        assertEquals(guestDtos.get(0).getLastName(), guest1.getLastName());
        assertEquals(guestDtos.get(0).isCheckedin(), guest1.isCheckedin());
        assertEquals(guestDtos.get(0).getBoxId(), guest1.getBox().getId());
        assertEquals(guestDtos.get(1).getFirstName(), guest2.getFirstName());
        assertEquals(guestDtos.get(1).getLastName(), guest2.getLastName());
        assertEquals(guestDtos.get(1).isCheckedin(), guest2.isCheckedin());
        assertEquals(guestDtos.get(1).getBoxId(), guest2.getBox().getId());
    }
}
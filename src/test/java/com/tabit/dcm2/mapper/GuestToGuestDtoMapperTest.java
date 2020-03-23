package com.tabit.dcm2.mapper;

import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.GuestDto;
import com.tabit.dcm2.entity.RandomGuest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GuestToGuestDtoMapperTest {

    GuestToGuestDtoMapper mapper = new GuestToGuestDtoMapper();

    @Test
    public void map() {
        Guest guest = RandomGuest.createRandomGuest();

        GuestDto guestDto = mapper.map(guest);
        assertTrue(guestDto.getFirstName().equals(guest.getFirstName()));
        assertTrue(guestDto.getLastName().equals(guest.getLastName()));
        assertTrue(guestDto.getBoxId()==guest.getBox().getId());
    }

    @Test
    public void testMap() {
        Guest guest1 = RandomGuest.createRandomGuest();
        Guest guest2 = RandomGuest.createRandomGuest();

        List<Guest> guests = new ArrayList<>();
        guests.add(guest1);
        guests.add(guest2);

        List<GuestDto> guestDtos = mapper.map(guests);
        assertTrue(guests.size() == guestDtos.size());
        assertTrue(guestDtos.get(0).getFirstName().equals(guest1.getFirstName()));
        assertTrue(guestDtos.get(0).getLastName().equals(guest1.getLastName()));
        assertTrue(guestDtos.get(0).getBoxId()==guest1.getBox().getId());
        assertTrue(guestDtos.get(1).getFirstName().equals(guest2.getFirstName()));
        assertTrue(guestDtos.get(1).getLastName().equals(guest2.getLastName()));
        assertTrue(guestDtos.get(1).getBoxId()==guest2.getBox().getId());
    }
}
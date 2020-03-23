package com.tabit.dcm2.mapper;

import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.GuestDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GuestDtoToGuestMapperTest {
    GuestDtoToGuestMapper mapper = new GuestDtoToGuestMapper();

    @Test
    public void map() {
        GuestDto guestDto = new GuestDto();
        guestDto.setBoxId(1);
        guestDto.setFirstName("FirstName");
        guestDto.setLastName("LastName");

        Guest guest = mapper.map(guestDto);
        assertTrue(guest.getFirstName().equals(guestDto.getFirstName()));
        assertTrue(guest.getLastName().equals(guestDto.getLastName()));
        assertTrue(guest.getBox().getId()==guestDto.getBoxId());
    }

    @Test
    public void testMap() {
        GuestDto guestDto1 = new GuestDto();
        guestDto1.setBoxId(1);
        guestDto1.setFirstName("FirstName1");
        guestDto1.setLastName("LastName1");

        GuestDto guestDto2 = new GuestDto();
        guestDto2.setBoxId(2);
        guestDto2.setFirstName("FirstName2");
        guestDto2.setLastName("LastName2");

        List<GuestDto> guestDtos = new ArrayList<>();
        guestDtos.add(guestDto1);
        guestDtos.add(guestDto2);

        List<Guest> guests = mapper.map(guestDtos);
        assertTrue(guests.size() == guests.size());
        assertTrue(guests.get(0).getFirstName().equals(guestDto1.getFirstName()));
        assertTrue(guests.get(0).getLastName().equals(guestDto1.getLastName()));
        assertTrue(guests.get(0).getBox().getId()==guestDto1.getBoxId());
        assertTrue(guests.get(1).getFirstName().equals(guestDto2.getFirstName()));
        assertTrue(guests.get(1).getLastName().equals(guestDto2.getLastName()));
        assertTrue(guests.get(1).getBox().getId()==guestDto2.getBoxId());
    }
}
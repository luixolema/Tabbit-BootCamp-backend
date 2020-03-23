package com.tabit.dcm2.mapper;

import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.service.GuestDto;
import com.tabit.dcm2.service.RandomGuestDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GuestDtoToGuestMapperTest {
    private GuestDtoToGuestMapper mapper = new GuestDtoToGuestMapper();

    @Test
    public void map() {
        GuestDto guestDto = RandomGuestDto.createRandomGuestDto();

        Guest guest = mapper.map(guestDto);
        assertEquals(guest.getFirstName(), guestDto.getFirstName());
        assertEquals(guest.getLastName(), guestDto.getLastName());
        assertEquals(guest.getBox().getId(), guestDto.getBoxId());
        assertEquals(guest.isCheckedin(), guestDto.isCheckedin());
    }

    @Test
    public void testMap() {
        GuestDto guestDto1 = RandomGuestDto.createRandomGuestDto();
        GuestDto guestDto2 = RandomGuestDto.createRandomGuestDto();

        List<GuestDto> guestDtos = new ArrayList<>();
        guestDtos.add(guestDto1);
        guestDtos.add(guestDto2);

        List<Guest> guests = mapper.map(guestDtos);
        assertEquals(guests.size(), guests.size());
        assertEquals(guests.get(0).getFirstName(), guestDto1.getFirstName());
        assertEquals(guests.get(0).getLastName(), guestDto1.getLastName());
        assertEquals(guests.get(0).getBox().getId(), guestDto1.getBoxId());
        assertEquals(guests.get(0).isCheckedin(), guestDto1.isCheckedin());
        assertEquals(guests.get(1).getFirstName(), guestDto2.getFirstName());
        assertEquals(guests.get(1).getLastName(), guestDto2.getLastName());
        assertEquals(guests.get(1).getBox().getId(), guestDto2.getBoxId());
        assertEquals(guests.get(1).isCheckedin(), guestDto2.isCheckedin());
    }
}
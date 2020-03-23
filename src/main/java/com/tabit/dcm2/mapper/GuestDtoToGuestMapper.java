package com.tabit.dcm2.mapper;

import com.tabit.dcm2.entity.Box;
import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.GuestDto;

import java.util.List;
import java.util.stream.Collectors;

public class GuestDtoToGuestMapper implements IMapper<GuestDto, Guest> {
    @Override
    public Guest map(GuestDto source) {
        Guest guest = new Guest();
        Box box = new Box();
        box.setId(source.getBoxId());
        guest.setBox(box);
        box.setGuest(guest);
        guest.setFirstName(source.getFirstName());
        guest.setLastName(source.getLastName());
        return guest;
    }

    @Override
    public List<Guest> map(List<GuestDto> source) {
        return source.stream().map(s -> this.map(s)).collect(Collectors.toList());
    }
}

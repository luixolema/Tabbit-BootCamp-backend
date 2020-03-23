package com.tabit.dcm2.mapper;

import com.tabit.dcm2.entity.Box;
import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.service.GuestDto;

import java.util.List;
import java.util.stream.Collectors;

public class GuestDtoToGuestMapper implements IMapper<GuestDto, Guest> {
    @Override
    public Guest map(GuestDto source) {
        Guest guest = new Guest();
        if(source.getBoxId() != 0) {
            Box box = new Box();
            box.setId(source.getBoxId());
            guest.setBox(box);
            box.setGuest(guest);
        }
        guest.setFirstName(source.getFirstName());
        guest.setLastName(source.getLastName());
        guest.setCheckedin(source.isCheckedin());
        return guest;
    }

    @Override
    public List<Guest> map(List<GuestDto> source) {
        return source.stream().map(this::map).collect(Collectors.toList());
    }
}

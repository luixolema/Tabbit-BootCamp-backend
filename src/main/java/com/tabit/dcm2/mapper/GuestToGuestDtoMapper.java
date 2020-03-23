package com.tabit.dcm2.mapper;

import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.service.GuestDto;

import java.util.List;
import java.util.stream.Collectors;

public class GuestToGuestDtoMapper implements IMapper<Guest, GuestDto> {
    @Override
    public GuestDto map(Guest source) {
        GuestDto guestDto = new GuestDto();
        guestDto.setBoxId(source.getBox().getId());
        guestDto.setFirstName(source.getFirstName());
        guestDto.setLastName(source.getLastName());
        guestDto.setCheckedin(source.isCheckedin());
        return guestDto;
    }

    @Override
    public List<GuestDto> map(List<Guest> source) {
        return source.stream().map(this::map).collect(Collectors.toList());
    }
}

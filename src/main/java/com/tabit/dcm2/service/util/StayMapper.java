package com.tabit.dcm2.service.util;

import com.tabit.dcm2.controller.requests.StayRequest;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.service.dto.GuestPersonalDetailsDto;
import com.tabit.dcm2.service.dto.StayDetailsDto;
import com.tabit.dcm2.service.dto.StayDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StayMapper {

    @Mapping(target = "id", ignore = true)
    void updateStay(@MappingTarget Stay stay, GuestPersonalDetailsDto guestPersonalDetailsDto);

    Stay toStay(StayRequest stayRequest);

    GuestPersonalDetailsDto toGuestPersonalDetailsDto(Stay stay);

    StayDetailsDto toStayDetailsDto(Stay stay);

    default StayDto toStayDTO(Stay stay) {
        GuestPersonalDetailsDto guestPersonalDetailsDto = toGuestPersonalDetailsDto(stay);
        StayDetailsDto stayDetailsDto = toStayDetailsDto(stay);

        StayDto stayDto = new StayDto();
        stayDto.setGuestPersonalDetails(guestPersonalDetailsDto);
        stayDto.setStayDetails(stayDetailsDto);

        return stayDto;
    }
}


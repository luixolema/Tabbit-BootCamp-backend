package com.tabit.dcm2.mapper;

import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.GuestDto;
import org.springframework.stereotype.Component;

@Component
public class MapperFactory implements IMapperFactory {

    @Override
    public IMapper getMapper(Class source, Class target) throws Exception {
        if(source == GuestDto.class && target == Guest.class) {
            return new GuestDtoToGuestMapper();
        }
        if(source == Guest.class && target == GuestDto.class) {
            return new GuestToGuestDtoMapper();
        }
        throw new Exception("Can't find mapper for given source class " + source + " and target class " + target);
    }
}

package com.tabit.dcm2.service.util;

import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.RandomGuest;
import com.tabit.dcm2.service.dto.GuestPersonalDetailsDto;
import com.tabit.dcm2.service.dto.RandomGuestPersonalDetailsDto;
import com.tabit.dcm2.testutils.GuestMappingAssertions;
import org.junit.Test;

public class GuestMapperTest {
    private GuestMapper mapper = new GuestMapper();

    @Test
    public void mapPersonalDetailsFromDto_shall_map_the_data_of_dto_to_the_guest() {
        // given
        Guest randomGuest = RandomGuest.createRandomGuest();
        GuestPersonalDetailsDto guestPersonalDetailsDto = RandomGuestPersonalDetailsDto.createRandomGuestPersonalDetailsDto();

        // when
        mapper.mapPersonalDetailsFromDto(randomGuest, guestPersonalDetailsDto);

        // then
        GuestMappingAssertions.assertPersonalDetails(randomGuest, guestPersonalDetailsDto);
    }
}
package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.exception.BeanValidationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class GuestPersonalDetailsDtoTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void invalid_dto_throws_exception() {
        expectedException.expect(BeanValidationException.class);
        expectedException.expectMessage("GuestPersonalDetailsDto.email: Email is not valid");
        RandomGuestPersonalDetailsDto.createRandomGuestPersonalDetailsDtoBuilder().withEmail("ha@ta").build();
    }
}
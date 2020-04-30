package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.exception.BeanValidationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;

public class AbstractGuestPersonalDetailsDtoTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void invalid_dto_with_not_valid_email_throws_exception() {
        expectedException.expect(BeanValidationException.class);
        expectedException.expectMessage("AbstractGuestPersonalDetailsDto.email: Email is not valid");
        RandomGuestPersonalDetailsDto.createRandomGuestPersonalDetailsDtoBuilder().withEmail("ha@ta").build();
    }

    @Test
    public void invalid_dto_with_birthdate_in_future_throws_exception() {
        expectedException.expect(BeanValidationException.class);
        expectedException.expectMessage("AbstractGuestPersonalDetailsDto.birthDate: must not be in the future");
        RandomGuestPersonalDetailsDto.createRandomGuestPersonalDetailsDtoBuilder().withBirthDate(LocalDate.now().plusDays(5)).build();
    }
}
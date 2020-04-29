package com.tabit.dcm2.service.dto;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.exception.BeanValidationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class GuestDetailDtoTest {
    private static final String ERROR_MESSAGE = "GuestDetailDto: Exactly one of stayDto or guestPersonalDetails must be set";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @DataPoints
    public static BeanValidationExceptionTestCase<GuestDetailDto.Builder>[] testCases = ImmutableList.of(
            noDtoIsSet(),
            bothDtosAreSet()).toArray(new BeanValidationExceptionTestCase[0]);

    @Theory
    @Test
    public void invalid_dto_throws_exception(BeanValidationExceptionTestCase<GuestDetailDto.Builder> testCase) {
        expectedException.expect(BeanValidationException.class);
        expectedException.expectMessage(testCase.getExpectedMessage());
        testCase.getInvalidDto().build();
    }

    private static BeanValidationExceptionTestCase<GuestDetailDto.Builder> noDtoIsSet() {
        return new BeanValidationExceptionTestCase<GuestDetailDto.Builder>()
                .withInvalidDto(RandomGuestDetailDto.createRandomGuestDetailDtoBuilder().withGuestPersonalDetailsDto(null).withStayDto(null))
                .withExpectedMessage(ERROR_MESSAGE);
    }

    private static BeanValidationExceptionTestCase<GuestDetailDto.Builder> bothDtosAreSet() {
        return new BeanValidationExceptionTestCase<GuestDetailDto.Builder>()
                .withInvalidDto(RandomGuestDetailDto.createRandomGuestDetailDtoBuilder()
                        .withGuestPersonalDetailsDto(RandomGuestPersonalDetailsDto.createRandomGuestPersonalDetailsDto())
                        .withStayDto(RandomStayDto.createRandomStayDto()))
                .withExpectedMessage(ERROR_MESSAGE);
    }
}
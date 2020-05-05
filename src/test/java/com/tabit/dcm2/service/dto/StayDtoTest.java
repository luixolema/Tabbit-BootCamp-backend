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

import java.util.Collections;
import java.util.Optional;


@RunWith(Theories.class)
public class StayDtoTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @DataPoints
    public static BeanValidationExceptionTestCase<StayDto.Builder>[] testCases = ImmutableList.of(
            stayDtoWithEmptyPhone(),
            stayDtoWithEmptyPassportId()
    ).toArray(new BeanValidationExceptionTestCase[0]);

    @Theory
    @Test
    public void invalid_dto_throws_exception(BeanValidationExceptionTestCase<StayDto.Builder> testCase) {
        expectedException.expect(BeanValidationException.class);
        expectedException.expectMessage(testCase.getExpectedMessage());
        testCase.getInvalidDto().build();
    }

    private static BeanValidationExceptionTestCase<StayDto.Builder> stayDtoWithEmptyPhone() {
        StayDetailsDto stayDetailsDto = RandomStayDetailsDto.createRandomStayDetailsDto();
        GuestPersonalDetailsDto personalDetailsDto = RandomGuestPersonalDetailsDto.createRandomGuestPersonalDetailsDto();
        personalDetailsDto.phone = Optional.empty();

        StayDto.Builder checkInDtoBuilder = RandomStayDto.createRandomStayDtoBuilder()
                .withGuestPersonalDetails(personalDetailsDto)
                .withStayDetails(stayDetailsDto)
                .withLoanDetails(Collections.emptyList());


        return new BeanValidationExceptionTestCase<StayDto.Builder>()
                .withInvalidDto(checkInDtoBuilder)
                .withExpectedMessage("StayDto.GuestPersonalDetailsDto.phone: Optional is not valid");
    }

    private static BeanValidationExceptionTestCase<StayDto.Builder> stayDtoWithEmptyPassportId() {
        StayDetailsDto stayDetailsDto = RandomStayDetailsDto.createRandomStayDetailsDto();
        GuestPersonalDetailsDto personalDetailsDto = RandomGuestPersonalDetailsDto.createRandomGuestPersonalDetailsDto();
        personalDetailsDto.passportId = Optional.empty();

        StayDto.Builder checkInDtoBuilder = RandomStayDto.createRandomStayDtoBuilder()
                .withGuestPersonalDetails(personalDetailsDto)
                .withStayDetails(stayDetailsDto)
                .withLoanDetails(Collections.emptyList());


        return new BeanValidationExceptionTestCase<StayDto.Builder>()
                .withInvalidDto(checkInDtoBuilder)
                .withExpectedMessage("StayDto.GuestPersonalDetailsDto.passportId: Optional is not valid");
    }

}

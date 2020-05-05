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

import java.util.Optional;

@RunWith(Theories.class)
public class CheckInDtoTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @DataPoints
    public static BeanValidationExceptionTestCase<CheckInDto.Builder>[] testCases = ImmutableList.of(
            checkedInDtoWithEmptyPhone(),
            checkInDtoWithEmptyPassportId()
    ).toArray(new BeanValidationExceptionTestCase[0]);

    @Theory
    @Test
    public void invalid_dto_throws_exception(BeanValidationExceptionTestCase<CheckInDto.Builder> testCase) {
        expectedException.expect(BeanValidationException.class);
        expectedException.expectMessage(testCase.getExpectedMessage());
        testCase.getInvalidDto().build();
    }

    private static BeanValidationExceptionTestCase<CheckInDto.Builder> checkedInDtoWithEmptyPhone() {
        StayDetailsForCheckInDto stayDetailsForCheckInDto = RandomStayDetailsForCheckInDto.createRandomStayDetailsForCheckInDto();
        GuestPersonalDetailsDto personalDetailsDto = RandomGuestPersonalDetailsDto.createRandomGuestPersonalDetailsDto();
        personalDetailsDto.phone = Optional.empty();

        CheckInDto.Builder checkInDtoBuilder = RandomCheckInDto.createRandomCheckInDtoBuilder()
                .withGuestPersonalDetails(personalDetailsDto)
                .withStayDetails(stayDetailsForCheckInDto);


        return new BeanValidationExceptionTestCase<CheckInDto.Builder>()
                .withInvalidDto(checkInDtoBuilder)
                .withExpectedMessage("CheckInDto.GuestPersonalDetailsDto.phone: Optional is not valid");
    }

    private static BeanValidationExceptionTestCase<CheckInDto.Builder> checkInDtoWithEmptyPassportId() {
        StayDetailsForCheckInDto stayDetailsForCheckInDto = RandomStayDetailsForCheckInDto.createRandomStayDetailsForCheckInDto();
        GuestPersonalDetailsDto personalDetailsDto = RandomGuestPersonalDetailsDto.createRandomGuestPersonalDetailsDto();
        personalDetailsDto.passportId = Optional.empty();

        CheckInDto.Builder checkInDtoBuilder = RandomCheckInDto.createRandomCheckInDtoBuilder()
                .withGuestPersonalDetails(personalDetailsDto)
                .withStayDetails(stayDetailsForCheckInDto);


        return new BeanValidationExceptionTestCase<CheckInDto.Builder>()
                .withInvalidDto(checkInDtoBuilder)
                .withExpectedMessage("CheckInDto.GuestPersonalDetailsDto.passportId: Optional is not valid");
    }

}

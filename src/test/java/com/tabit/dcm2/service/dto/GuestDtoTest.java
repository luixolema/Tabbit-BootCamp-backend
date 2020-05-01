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
public class GuestDtoTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @DataPoints
    public static BeanValidationExceptionTestCase<GuestDto.Builder>[] testCases = ImmutableList.of(
            checkedInGuestWithoutBoxNumber()).toArray(new BeanValidationExceptionTestCase[0]);

    @Theory
    @Test
    public void invalid_dto_throws_exception(BeanValidationExceptionTestCase<GuestDto.Builder> testCase) {
        expectedException.expect(BeanValidationException.class);
        expectedException.expectMessage(testCase.getExpectedMessage());
        testCase.getInvalidDto().build();
    }

    private static BeanValidationExceptionTestCase<GuestDto.Builder> checkedInGuestWithoutBoxNumber() {
        return new BeanValidationExceptionTestCase<GuestDto.Builder>()
                .withInvalidDto(RandomGuestDto.createRandomGuestDtoBuilder().withCheckedin(true).withBoxNumber(null))
                .withExpectedMessage("GuestDto.boxNumber: Boxnumber must be set if checkedIn");
    }

    private static BeanValidationExceptionTestCase<GuestDto.Builder> notCheckedInGuestWithBoxNumber() {
        return new BeanValidationExceptionTestCase<GuestDto.Builder>()
                .withInvalidDto(RandomGuestDto.createRandomGuestDtoBuilder().withCheckedin(true).withBoxNumber(null))
                .withExpectedMessage("GuestDto.boxNumber: Boxnumber must not be set if not checkedIn");
    }
}
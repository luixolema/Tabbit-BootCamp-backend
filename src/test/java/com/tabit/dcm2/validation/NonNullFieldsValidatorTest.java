package com.tabit.dcm2.validation;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.service.dto.GuestPersonalDetailsDto;
import com.tabit.dcm2.service.dto.RandomGuestPersonalDetailsDto;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class NonNullFieldsValidatorTest {
    private static final String MESSAGE = "Null value is not allowed";

    @DataPoints
    public static ValidationTestCase<GuestPersonalDetailsDto>[] testCases = ImmutableList.of(
            validNoNullFields(),
            invalidOneNullField()).toArray(new ValidationTestCase[0]);

    @Theory
    @Test
    public void validates_correct(ValidationTestCase<GuestPersonalDetailsDto> validationTestCase) {
        ValidationResult validationResult = new NonNullFieldsValidator(() -> validationTestCase.toValidate).validate();

        Assertions.assertThat(validationResult).isEqualTo(validationTestCase.expectedResult);
    }

    private static ValidationTestCase<GuestPersonalDetailsDto> validNoNullFields() {
        return new ValidationTestCase<GuestPersonalDetailsDto>().withToValidate(RandomGuestPersonalDetailsDto.createRandomGuestPersonalDetailsDto())
                .withExpectedResult(ValidationResult.noError());
    }

    private static ValidationTestCase<GuestPersonalDetailsDto> invalidOneNullField() {
        return new ValidationTestCase<GuestPersonalDetailsDto>().withToValidate(new GuestPersonalDetailsDto())
                .withExpectedResult(ValidationResult.withError(GuestPersonalDetailsDto.class.getSimpleName() + "." + "id", MESSAGE));
    }
}
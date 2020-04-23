package com.tabit.dcm2.validation;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.validation.LocalDateAfterValidator.LocalDateAfterValidatorInput;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.time.LocalDate;

@RunWith(Theories.class)
public class LocalDateAfterValidatorTest {
    private static final String PROPERTY = "leave";
    private static final String PROPERTY_FIRST = "arrive";
    private static final String MESSAGE = "must be after arrive";
    private static final ValidationResult error = ValidationResult.withError(PROPERTY, MESSAGE);
    private static final LocalDate NOW = LocalDate.now();

    @DataPoints
    public static ValidationTestCase<LocalDateAfterValidatorInput>[] testCases = ImmutableList.of(
            invalidIsBefore(),
            invalidIsSame(),
            validIsAfter()).toArray(new ValidationTestCase[0]);

    @Theory
    @Test
    public void validates_correct(ValidationTestCase<LocalDateAfterValidatorInput> validationTestCase) {
        ValidationResult validationResult = new LocalDateAfterValidator(PROPERTY, () -> validationTestCase.toValidate).validate();

        Assertions.assertThat(validationResult).isEqualTo(validationTestCase.expectedResult);
    }

    private static ValidationTestCase<LocalDateAfterValidatorInput> invalidIsBefore() {
        return new ValidationTestCase<LocalDateAfterValidatorInput>().withToValidate(new LocalDateAfterValidatorInput(PROPERTY_FIRST, () -> NOW, () -> NOW.minusYears(1))).withExpectedResult(error);
    }

    private static ValidationTestCase<LocalDateAfterValidatorInput> invalidIsSame() {
        return new ValidationTestCase<LocalDateAfterValidatorInput>().withToValidate(new LocalDateAfterValidatorInput(PROPERTY_FIRST, () -> NOW, () -> NOW)).withExpectedResult(error);
    }

    private static ValidationTestCase<LocalDateAfterValidatorInput> validIsAfter() {
        return new ValidationTestCase<LocalDateAfterValidatorInput>().withToValidate(new LocalDateAfterValidatorInput(PROPERTY_FIRST, () -> NOW, () -> NOW.plusDays(1))).withExpectedResult(ValidationResult.noError());
    }
}
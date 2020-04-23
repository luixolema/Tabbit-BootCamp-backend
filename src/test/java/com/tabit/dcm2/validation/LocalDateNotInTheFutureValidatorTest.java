package com.tabit.dcm2.validation;

import com.google.common.collect.ImmutableList;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.time.LocalDate;

@RunWith(Theories.class)
public class LocalDateNotInTheFutureValidatorTest {
    private static final String PROPERTY = "dive";
    private static final String MESSAGE = "must not be in the future";
    private static final ValidationResult error = ValidationResult.withError(PROPERTY, MESSAGE);
    private static final LocalDate NOW = LocalDate.now();

    @DataPoints
    public static ValidationTestCase<LocalDate>[] testCases = ImmutableList.of(
            validIsPast(),
            validIsToday(),
            invalidIsFuture()).toArray(new ValidationTestCase[0]);

    @Theory
    @Test
    public void validates_correct(ValidationTestCase<LocalDate> validationTestCase) {
        ValidationResult validationResult = new LocalDateNotInTheFutureValidator(PROPERTY, () -> validationTestCase.toValidate).validate();

        Assertions.assertThat(validationResult).isEqualTo(validationTestCase.expectedResult);
    }

    private static ValidationTestCase<LocalDate> validIsPast() {
        return new ValidationTestCase<LocalDate>().withToValidate(NOW.minusDays(1)).withExpectedResult(ValidationResult.noError());
    }

    private static ValidationTestCase<LocalDate> validIsToday() {
        return new ValidationTestCase<LocalDate>().withToValidate(NOW).withExpectedResult(ValidationResult.noError());
    }

    private static ValidationTestCase<LocalDate> invalidIsFuture() {
        return new ValidationTestCase<LocalDate>().withToValidate(NOW.plusDays(1)).withExpectedResult(error);
    }
}
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
public class LocalDateNotInThePastValidatorTest {
    private static final String PROPERTY = "leave";
    private static final String MESSAGE = "must not be in the past";
    private static final ValidationResult error = ValidationResult.withError(PROPERTY, MESSAGE);
    private static final LocalDate NOW = LocalDate.now();

    @DataPoints
    public static ValidationTestCase<LocalDate>[] testCases = ImmutableList.of(
            invalidIsPast(),
            invalidIsToday(),
            validIsFuture()).toArray(new ValidationTestCase[0]);

    @Theory
    @Test
    public void validates_correct(ValidationTestCase<LocalDate> validationTestCase) {
        ValidationResult validationResult = new LocalDateNotInThePastValidator(PROPERTY, () -> validationTestCase.toValidate).validate();

        Assertions.assertThat(validationResult).isEqualTo(validationTestCase.expectedResult);
    }

    private static ValidationTestCase<LocalDate> invalidIsPast() {
        return new ValidationTestCase<LocalDate>().withToValidate(NOW.minusDays(1)).withExpectedResult(error);
    }

    private static ValidationTestCase<LocalDate> invalidIsToday() {
        return new ValidationTestCase<LocalDate>().withToValidate(NOW).withExpectedResult(ValidationResult.noError());
    }

    private static ValidationTestCase<LocalDate> validIsFuture() {
        return new ValidationTestCase<LocalDate>().withToValidate(NOW.plusDays(1)).withExpectedResult(ValidationResult.noError());
    }
}
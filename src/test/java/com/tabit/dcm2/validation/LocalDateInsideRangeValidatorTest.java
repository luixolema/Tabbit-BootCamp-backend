package com.tabit.dcm2.validation;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.validation.LocalDateInsideRangeValidator.LocalDateInsideRangeValidatorInput;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.time.LocalDate;

@RunWith(Theories.class)
public class LocalDateInsideRangeValidatorTest {
    private static final String PROPERTY = "insideRange";
    private static final String PROPERTY_START = "start";
    private static final String PROPERTY_END = "end";
    private static final String MESSAGE = "must be between start and end";
    private static final ValidationResult error = ValidationResult.withError(PROPERTY, MESSAGE);
    private static final LocalDate START = LocalDate.now();
    private static final LocalDate END = START.plusDays(10);

    @DataPoints
    public static ValidationTestCase<LocalDateInsideRangeValidatorInput>[] testCases = ImmutableList.of(
            invalidIsBefore(),
            invalidIsAfter(),
            validIsInsideRange(),
            validIsSameAsEnd(),
            validIsSameAsStart()).toArray(new ValidationTestCase[0]);

    @Theory
    @Test
    public void validates_correct(ValidationTestCase<LocalDateInsideRangeValidatorInput> validationTestCase) {
        ValidationResult validationResult = new LocalDateInsideRangeValidator(PROPERTY, validationTestCase.toValidate).validate();

        Assertions.assertThat(validationResult).isEqualTo(validationTestCase.expectedResult);
    }

    private static ValidationTestCase<LocalDateInsideRangeValidatorInput> invalidIsBefore() {
        return new ValidationTestCase<LocalDateInsideRangeValidatorInput>().withToValidate(new LocalDateInsideRangeValidatorInput(PROPERTY_START, PROPERTY_END, () -> START, () -> END, () -> START.minusDays(1))).withExpectedResult(error);
    }

    private static ValidationTestCase<LocalDateInsideRangeValidatorInput> validIsInsideRange() {
        return new ValidationTestCase<LocalDateInsideRangeValidatorInput>().withToValidate(new LocalDateInsideRangeValidatorInput(PROPERTY_START, PROPERTY_END, () -> START, () -> END, () -> START.plusDays(1))).withExpectedResult(ValidationResult.noError());
    }

    private static ValidationTestCase<LocalDateInsideRangeValidatorInput> validIsSameAsStart() {
        return new ValidationTestCase<LocalDateInsideRangeValidatorInput>().withToValidate(new LocalDateInsideRangeValidatorInput(PROPERTY_START, PROPERTY_END, () -> START, () -> END, () -> START)).withExpectedResult(ValidationResult.noError());
    }

    private static ValidationTestCase<LocalDateInsideRangeValidatorInput> validIsSameAsEnd() {
        return new ValidationTestCase<LocalDateInsideRangeValidatorInput>().withToValidate(new LocalDateInsideRangeValidatorInput(PROPERTY_START, PROPERTY_END, () -> START, () -> END, () -> END)).withExpectedResult(ValidationResult.noError());
    }

    private static ValidationTestCase<LocalDateInsideRangeValidatorInput> invalidIsAfter() {
        return new ValidationTestCase<LocalDateInsideRangeValidatorInput>().withToValidate(new LocalDateInsideRangeValidatorInput(PROPERTY_START, PROPERTY_END, () -> START, () -> END, () -> END.plusDays(1))).withExpectedResult(error);
    }
}
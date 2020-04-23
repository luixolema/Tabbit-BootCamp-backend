package com.tabit.dcm2.validation;

import com.google.common.collect.ImmutableList;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class PositiveIntegerValidatorTest {
    private static final String PROPERTY = "numberField";
    private static final String MESSAGE = "Integer must be 0 or greater";
    private static final ValidationResult error = ValidationResult.withError(PROPERTY, MESSAGE);

    @DataPoints
    public static ValidationTestCase<Integer>[] testCases = ImmutableList.of(
            validPositivInteger(),
            validZero(),
            invalidNegativInteger()).toArray(new ValidationTestCase[0]);

    @Theory
    @Test
    public void validates_correct(ValidationTestCase<Integer> validationTestCase) {
        ValidationResult validationResult = new PositiveIntegerValidator(PROPERTY, () -> validationTestCase.toValidate).validate();

        Assertions.assertThat(validationResult).isEqualTo(validationTestCase.expectedResult);
    }

    private static ValidationTestCase<Integer> validPositivInteger() {
        return new ValidationTestCase<Integer>().withToValidate(1).withExpectedResult(ValidationResult.noError());
    }

    private static ValidationTestCase<Integer> validZero() {
        return new ValidationTestCase<Integer>().withToValidate(0).withExpectedResult(ValidationResult.noError());
    }

    private static ValidationTestCase<Integer> invalidNegativInteger() {
        return new ValidationTestCase<Integer>().withToValidate(-1).withExpectedResult(error);
    }
}
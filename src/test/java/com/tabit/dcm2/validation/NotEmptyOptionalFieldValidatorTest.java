package com.tabit.dcm2.validation;

import com.google.common.collect.ImmutableList;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.util.Optional;

@RunWith(Theories.class)
public class NotEmptyOptionalFieldValidatorTest {
    private static final String BEAN_PROPERTY = "property";
    private static final String MESSAGE = "Optional must not be empty";

    @DataPoints
    public static ValidationTestCase<Optional<String>>[] testCases = ImmutableList.of(
            validOptionalPresent(),
            invalidOptionalEmpty()).toArray(new ValidationTestCase[0]);

    @Theory
    @Test
    public void validates_correct(ValidationTestCase<Optional<String>> validationTestCase) {
        ValidationResult validationResult = new NotEmptyOptionalFieldValidator(BEAN_PROPERTY, () -> validationTestCase.toValidate).validate();

        Assertions.assertThat(validationResult).isEqualTo(validationTestCase.expectedResult);
    }

    private static ValidationTestCase<Optional<String>> validOptionalPresent() {
        return new ValidationTestCase<Optional<String>>().withToValidate(Optional.of("test"))
                .withExpectedResult(ValidationResult.noError());
    }

    private static ValidationTestCase<Optional<String>> invalidOptionalEmpty() {
        return new ValidationTestCase<Optional<String>>().withToValidate(Optional.empty())
                .withExpectedResult(ValidationResult.withError(BEAN_PROPERTY, MESSAGE));
    }
}
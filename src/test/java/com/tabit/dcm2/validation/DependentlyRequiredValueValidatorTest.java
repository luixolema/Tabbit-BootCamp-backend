package com.tabit.dcm2.validation;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.validation.DependentlyRequiredValueValidator.DependentlyRequiredValueValidatorInput;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.util.Optional;

@SuppressWarnings("PMD")
@RunWith(Theories.class)
public class DependentlyRequiredValueValidatorTest {

    private static final String BEAN_PROPERTY = "boxnumber";
    private static final String DEPENDEE_PROPERTY = "CheckIn";
    private static final String DEPENDENTLY_REQUIRED_PROPERTY = "BoxNumber";

    @DataPoints
    public static ValidationTestCase<DependentlyRequiredValueValidatorInput<String>>[] testCases = ImmutableList.of(
            checkInTrueBoxNumberDefinedThenValid(),
            checkInTrueBoxNumberUndefinedThenInvalid(),
            checkInFalseBoxNumberUndefinedThenValid(),
            checkInFalseBoxNumberDefinedThenInvalid()
    ).toArray(new ValidationTestCase[0]);

    @Theory
    @Test
    public void validates_correct(ValidationTestCase<DependentlyRequiredValueValidatorInput> validationTestCase) {
        ValidationResult validationResult = new DependentlyRequiredValueValidator<>(BEAN_PROPERTY, () -> validationTestCase.toValidate).validate();

        Assertions.assertThat(validationResult).isEqualTo(validationTestCase.expectedResult);
    }

    private static ValidationTestCase<DependentlyRequiredValueValidatorInput<String>> checkInTrueBoxNumberDefinedThenValid() {
        return new ValidationTestCase<DependentlyRequiredValueValidatorInput<String>>().withToValidate(
                new DependentlyRequiredValueValidatorInput<>(
                        DEPENDEE_PROPERTY,
                        DEPENDENTLY_REQUIRED_PROPERTY,
                        () -> true,
                        () -> Optional.of("test")
                )
        ).withExpectedResult(ValidationResult.noError());
    }

    private static ValidationTestCase<DependentlyRequiredValueValidatorInput<String>> checkInTrueBoxNumberUndefinedThenInvalid() {
        return new ValidationTestCase<DependentlyRequiredValueValidatorInput<String>>().withToValidate(
                new DependentlyRequiredValueValidatorInput<>(
                        DEPENDEE_PROPERTY,
                        DEPENDENTLY_REQUIRED_PROPERTY,
                        () -> true,
                        Optional::empty
                )
        ).withExpectedResult(ValidationResult.withError(BEAN_PROPERTY, "If CheckIn is set to true, it must be true that BoxNumber is defined"));
    }

    private static ValidationTestCase<DependentlyRequiredValueValidatorInput<String>> checkInFalseBoxNumberUndefinedThenValid() {
        return new ValidationTestCase<DependentlyRequiredValueValidatorInput<String>>().withToValidate(
                new DependentlyRequiredValueValidatorInput<>(
                        DEPENDEE_PROPERTY,
                        DEPENDENTLY_REQUIRED_PROPERTY,
                        () -> false,
                        Optional::empty
                )
        ).withExpectedResult(ValidationResult.noError());
    }

    private static ValidationTestCase<DependentlyRequiredValueValidatorInput<String>> checkInFalseBoxNumberDefinedThenInvalid() {
        return new ValidationTestCase<DependentlyRequiredValueValidatorInput<String>>().withToValidate(
                new DependentlyRequiredValueValidatorInput<>(
                        DEPENDEE_PROPERTY,
                        DEPENDENTLY_REQUIRED_PROPERTY,
                        () -> false,
                        () -> Optional.of("test")
                )
        ).withExpectedResult(ValidationResult.withError(BEAN_PROPERTY, "If CheckIn is set to false, it must be false that BoxNumber is defined"));
    }
}

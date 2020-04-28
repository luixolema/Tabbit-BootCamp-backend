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
public class DependentlyRequiredValueValidatorTest {

    private static final String DEPENDEE_PROPERTY = "CheckIn";
    private static final String DEPENDENTLY_REQUIRED_PROPERTY = "BoxNumber";

    @DataPoints
    public static ValidationTestCase<DependentlyRequiredValueValidator.DependentlyRequiredValueValidatorInput>[] testCases = ImmutableList.of(
            CheckInTrueBoxNumberDefinedThenValid(),
            CheckInTrueBoxNumberUndefinedThenInvalid(),
            CheckInFalseBoxNumberUndefinedThenValid(),
            CheckInFalseBoxNumberDefinedThenInvalid()
    ).toArray(new ValidationTestCase[0]);

    @Theory
    @Test
    public void validates_correct(ValidationTestCase<DependentlyRequiredValueValidator.DependentlyRequiredValueValidatorInput> validationTestCase) {
        ValidationResult validationResult = new DependentlyRequiredValueValidator<String>(DEPENDEE_PROPERTY, () -> validationTestCase.toValidate).validate();

        Assertions.assertThat(validationResult).isEqualTo(validationTestCase.expectedResult);
    }

    private static ValidationTestCase<DependentlyRequiredValueValidator.DependentlyRequiredValueValidatorInput> CheckInTrueBoxNumberDefinedThenValid() {
        return new ValidationTestCase<DependentlyRequiredValueValidator.DependentlyRequiredValueValidatorInput>().withToValidate(
                new DependentlyRequiredValueValidator.DependentlyRequiredValueValidatorInput<>(
                        DEPENDEE_PROPERTY,
                        DEPENDENTLY_REQUIRED_PROPERTY,
                        () -> true,
                        () -> Optional.of("test")
                )
        ).withExpectedResult(ValidationResult.noError());
    }

    private static ValidationTestCase<DependentlyRequiredValueValidator.DependentlyRequiredValueValidatorInput> CheckInTrueBoxNumberUndefinedThenInvalid() {
        return new ValidationTestCase<DependentlyRequiredValueValidator.DependentlyRequiredValueValidatorInput>().withToValidate(
                new DependentlyRequiredValueValidator.DependentlyRequiredValueValidatorInput<>(
                        DEPENDEE_PROPERTY,
                        DEPENDENTLY_REQUIRED_PROPERTY,
                        () -> true,
                        () -> Optional.empty()
                )
        ).withExpectedResult(ValidationResult.withError(DEPENDEE_PROPERTY, "If CheckIn is set to true, it must be true that BoxNumber is defined"));
    }

    private static ValidationTestCase<DependentlyRequiredValueValidator.DependentlyRequiredValueValidatorInput> CheckInFalseBoxNumberUndefinedThenValid() {
        return new ValidationTestCase<DependentlyRequiredValueValidator.DependentlyRequiredValueValidatorInput>().withToValidate(
                new DependentlyRequiredValueValidator.DependentlyRequiredValueValidatorInput<>(
                        DEPENDEE_PROPERTY,
                        DEPENDENTLY_REQUIRED_PROPERTY,
                        () -> false,
                        () -> Optional.empty()
                )
        ).withExpectedResult(ValidationResult.noError());
    }

    private static ValidationTestCase<DependentlyRequiredValueValidator.DependentlyRequiredValueValidatorInput> CheckInFalseBoxNumberDefinedThenInvalid() {
        return new ValidationTestCase<DependentlyRequiredValueValidator.DependentlyRequiredValueValidatorInput>().withToValidate(
                new DependentlyRequiredValueValidator.DependentlyRequiredValueValidatorInput<>(
                        DEPENDEE_PROPERTY,
                        DEPENDENTLY_REQUIRED_PROPERTY,
                        () -> false,
                        () -> Optional.of("test")
                )
        ).withExpectedResult(ValidationResult.withError(DEPENDEE_PROPERTY, "If CheckIn is set to false, it must be false that BoxNumber is defined"));
    }
}

package com.tabit.dcm2.validation;

import com.google.common.collect.ImmutableList;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class EmailValidatorTest {
    private static String PROPERTY = "email";
    private static String MESSAGE = "Email is not valid";
    private static final ValidationResult error = ValidationResult.withError(PROPERTY, MESSAGE);

    @DataPoints("emails")
    public static ValidationTestCase<String>[] emails = ImmutableList.of(
            validEmail(),
            validEmailThirdPartMaxLength(),
            invalidEmailFirstPartTooShort(),
            invalidEmailSecondPartTooShort(),
            invalidEmailLastPartTooShort(),
            invalidEmailLastPartTooLong(),
            invalidEmailNoAT(),
            invalidEmailNoDot(),
            invalidEmailSpaceAtTheBeginning(),
            invalidEmailSpaceAtTheEnd()
    ).toArray(new ValidationTestCase[0]);

    @Theory
    @Test
    public void validates_correct(@FromDataPoints("emails") ValidationTestCase<String> validationTestCase) {
        ValidationResult validationResult = new EmailValidator(PROPERTY, () -> validationTestCase.toValidate).validate();

        Assertions.assertThat(validationResult).isEqualTo(validationTestCase.expectedResult);
    }

    private static ValidationTestCase<String> validEmail() {
        String email = "ha.ta@ta.bi";
        return new ValidationTestCase<String>().withToValidate(email).withExpectedResult(ValidationResult.noError());
    }

    private static ValidationTestCase<String> validEmailThirdPartMaxLength() {
        String email = "ha@ta.bitabcd";
        return new ValidationTestCase<String>().withToValidate(email).withExpectedResult(ValidationResult.noError());
    }

    private static ValidationTestCase<String> invalidEmailFirstPartTooShort() {
        String email = "@ta.bi";
        return new ValidationTestCase<String>().withToValidate(email).withExpectedResult(error);
    }

    private static ValidationTestCase<String> invalidEmailSecondPartTooShort() {
        String email = "ha@t";
        return new ValidationTestCase<String>().withToValidate(email).withExpectedResult(error);
    }

    private static ValidationTestCase<String> invalidEmailLastPartTooShort() {
        String email = "ha@ta.b";
        return new ValidationTestCase<String>().withToValidate(email).withExpectedResult(error);
    }

    private static ValidationTestCase<String> invalidEmailLastPartTooLong() {
        String email = "ha@ta.bitabcde";
        return new ValidationTestCase<String>().withToValidate(email).withExpectedResult(error);
    }

    private static ValidationTestCase<String> invalidEmailNoAT() {
        String email = "hata.bit";
        return new ValidationTestCase<String>().withToValidate(email).withExpectedResult(error);
    }

    private static ValidationTestCase<String> invalidEmailNoDot() {
        String email = "ha@tabit";
        return new ValidationTestCase<String>().withToValidate(email).withExpectedResult(error);
    }

    private static ValidationTestCase<String> invalidEmailSpaceAtTheBeginning() {
        String email = " ha@ta.bit";
        return new ValidationTestCase<String>().withToValidate(email).withExpectedResult(error);
    }

    private static ValidationTestCase<String> invalidEmailSpaceAtTheEnd() {
        String email = "ha@ta.bit ";
        return new ValidationTestCase<String>().withToValidate(email).withExpectedResult(error);
    }
}
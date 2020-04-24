package com.tabit.dcm2.validation;

class ValidationTestCase<TO_VALIDATE> {
    TO_VALIDATE toValidate;
    ValidationResult expectedResult;

    ValidationTestCase<TO_VALIDATE> withToValidate(TO_VALIDATE toValidate) {
        this.toValidate = toValidate;
        return this;
    }

    ValidationTestCase<TO_VALIDATE> withExpectedResult(ValidationResult expectedResult) {
        this.expectedResult = expectedResult;
        return this;
    }
}
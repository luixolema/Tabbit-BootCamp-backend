package com.tabit.dcm2.validation;

import java.util.function.Supplier;
import java.util.regex.Pattern;

public class EmailValidator implements IBeanValidator {
    private static final String MESSAGE = "Email is not valid";
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private final String beanProperty;
    private final Supplier<String> toValidate;

    public EmailValidator(String beanProperty, Supplier<String> toValidate) {
        this.beanProperty = beanProperty;
        this.toValidate = toValidate;
    }

    @Override
    public ValidationResult validate() {
        return Pattern.matches(EMAIL_PATTERN, toValidate.get()) ? ValidationResult.noError() : ValidationResult.withError(beanProperty, MESSAGE);
    }
}

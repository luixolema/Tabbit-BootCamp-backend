package com.tabit.dcm2.validation;

import java.util.function.Supplier;
import java.util.regex.Pattern;

public class EmailValidator extends AbstractBeanValidator<String> {

    private static final String MESSAGE = "Email is not valid";
    private static String EMAIL_PATTERN = "^(.+){2,}@.{2,}[.].{2,10}$";

    public EmailValidator(String beanProperty, Supplier<String> toValidate) {
        super(beanProperty, toValidate);
    }

    @Override
    public ValidationResult validate() {
        return Pattern.matches(EMAIL_PATTERN, toValidate.get()) ? ValidationResult.noError() : ValidationResult.withError(beanProperty.get(), MESSAGE);
    }
}

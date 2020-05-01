package com.tabit.dcm2.validation;

import java.util.function.Supplier;

public class PositiveIntegerValidator implements IBeanValidator {
    private static final String MESSAGE = "Integer must be 0 or greater";

    private final String beanProperty;
    private final Supplier<Integer> toValidate;

    public PositiveIntegerValidator(String beanProperty, Supplier<Integer> toValidate) {
        this.beanProperty = beanProperty;
        this.toValidate = toValidate;
    }

    @Override
    public ValidationResult validate() {
        return toValidate.get() < 0 ? ValidationResult.withError(beanProperty, MESSAGE) : ValidationResult.noError();
    }
}

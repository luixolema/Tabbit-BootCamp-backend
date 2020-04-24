package com.tabit.dcm2.validation;

import java.util.function.Supplier;

public class PositiveIntegerValidator extends AbstractBeanValidator<Integer> {

    private static final String MESSAGE = "Integer must be 0 or greater";

    public PositiveIntegerValidator(String beanProperty, Supplier<Integer> toValidate) {
        super(beanProperty, toValidate);
    }

    @Override
    public ValidationResult validate() {
        return toValidate.get() < 0 ? ValidationResult.withError(beanProperty.get(), MESSAGE) : ValidationResult.noError();
    }
}

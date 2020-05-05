package com.tabit.dcm2.validation;

import java.util.Optional;
import java.util.function.Supplier;

public class NotEmptyOptionalFieldValidator implements IBeanValidator {
    private static final String MESSAGE = "Optional must not be empty";

    private final String beanProperty;
    private final Supplier<Optional<?>> toValidate;

    public NotEmptyOptionalFieldValidator(String beanProperty, Supplier<Optional<?>> toValidate) {
        this.beanProperty = beanProperty;
        this.toValidate = toValidate;
    }

    @Override
    public ValidationResult validate() {
        return toValidate.get().isPresent() ? ValidationResult.noError() : ValidationResult.withError(beanProperty, MESSAGE);
    }
}

package com.tabit.dcm2.validation;

import java.time.LocalDate;
import java.util.function.Supplier;

public class LocalDateNotInThePastValidator implements IBeanValidator {
    private static final String MESSAGE = "must not be in the past";

    private final String beanProperty;
    private final Supplier<LocalDate> toValidate;

    public LocalDateNotInThePastValidator(String beanProperty, Supplier<LocalDate> toValidate) {
        this.beanProperty = beanProperty;
        this.toValidate = toValidate;
    }

    @Override
    public ValidationResult validate() {
        return toValidate.get().isBefore(LocalDate.now()) ? ValidationResult.withError(beanProperty, MESSAGE) : ValidationResult.noError();
    }
}

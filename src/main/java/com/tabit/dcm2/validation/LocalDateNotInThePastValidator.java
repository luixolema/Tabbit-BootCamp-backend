package com.tabit.dcm2.validation;

import java.time.LocalDate;
import java.util.function.Supplier;

public class LocalDateNotInThePastValidator extends AbstractBeanValidator<LocalDate> {

    private static final String MESSAGE = "must not be in the past";

    public LocalDateNotInThePastValidator(String beanProperty, Supplier<LocalDate> toValidate) {
        super(beanProperty, toValidate);
    }

    @Override
    public ValidationResult validate() {
        return toValidate.get().isBefore(LocalDate.now()) ? ValidationResult.withError(beanProperty.get(), MESSAGE) : ValidationResult.noError();
    }
}

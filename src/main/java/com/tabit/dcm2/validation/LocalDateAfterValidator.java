package com.tabit.dcm2.validation;

import java.time.LocalDate;
import java.util.function.Supplier;

public class LocalDateAfterValidator implements IBeanValidator {
    private static final String MESSAGE = "must be after %s";

    private final String beanProperty;
    private final LocalDateAfterValidatorInput toValidate;

    public LocalDateAfterValidator(String beanProperty, LocalDateAfterValidatorInput toValidate) {
        this.beanProperty = beanProperty;
        this.toValidate = toValidate;
    }

    @Override
    public ValidationResult validate() {
        LocalDateAfterValidatorInput input = toValidate;
        return !input.after.get().isBefore(input.dateToCheckAgainst.get()) ? ValidationResult.noError() : ValidationResult.withError(beanProperty, String.format(MESSAGE, input.beanPropertyDateToCheckAgainst));
    }

    public static class LocalDateAfterValidatorInput {
        private final String beanPropertyDateToCheckAgainst;
        private final Supplier<LocalDate> dateToCheckAgainst;
        private final Supplier<LocalDate> after;

        public LocalDateAfterValidatorInput(String beanPropertyDateToCheckAgainst, Supplier<LocalDate> dateToCheckAgainst, Supplier<LocalDate> after) {
            this.beanPropertyDateToCheckAgainst = beanPropertyDateToCheckAgainst;
            this.dateToCheckAgainst = dateToCheckAgainst;
            this.after = after;
        }
    }
}

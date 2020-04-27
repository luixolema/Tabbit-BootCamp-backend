package com.tabit.dcm2.validation;

import java.time.LocalDate;
import java.util.function.Supplier;

public class LocalDateAfterValidator extends AbstractBeanValidator<LocalDateAfterValidator.LocalDateAfterValidatorInput> {

    private static final String MESSAGE = "must be after %s";

    public LocalDateAfterValidator(String beanProperty, Supplier<LocalDateAfterValidatorInput> toValidate) {
        super(beanProperty, toValidate);
    }

    @Override
    public ValidationResult validate() {
        LocalDateAfterValidatorInput input = toValidate.get();
        return !input.after.get().isBefore(input.dateToCheckAgainst.get()) ? ValidationResult.noError() : ValidationResult.withError(beanProperty.get(), String.format(MESSAGE, input.beanPropertyDateToCheckAgainst));
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

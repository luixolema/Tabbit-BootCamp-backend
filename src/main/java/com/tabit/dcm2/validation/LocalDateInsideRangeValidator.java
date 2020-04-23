package com.tabit.dcm2.validation;

import java.time.LocalDate;
import java.util.function.Supplier;

public class LocalDateInsideRangeValidator extends AbstractBeanValidator<LocalDateInsideRangeValidator.LocalDateInsideRangeValidatorInput> {

    private static final String MESSAGE = "must be between %s and %s";

    public LocalDateInsideRangeValidator(String beanProperty, Supplier<LocalDateInsideRangeValidatorInput> toValidate) {
        super(beanProperty, toValidate);
    }

    @Override
    public ValidationResult validate() {
        LocalDateInsideRangeValidatorInput input = toValidate.get();
        return isInsideRange(input) ? ValidationResult.noError() : ValidationResult.withError(beanProperty.get(), String.format(MESSAGE, input.beanPropertyRangeStart, input.beanPropertyRangeEnd));
    }

    private boolean isInsideRange(LocalDateInsideRangeValidatorInput input) {
        return !input.insideRange.get().isBefore(input.rangeStart.get()) && !input.insideRange.get().isAfter(input.rangeEnd.get());
    }

    public static class LocalDateInsideRangeValidatorInput {
        private final String beanPropertyRangeStart;
        private final String beanPropertyRangeEnd;
        private final Supplier<LocalDate> rangeStart;
        private final Supplier<LocalDate> rangeEnd;
        private final Supplier<LocalDate> insideRange;

        public LocalDateInsideRangeValidatorInput(String beanPropertyRangeStart, String beanPropertyRangeEnd, Supplier<LocalDate> rangeStart, Supplier<LocalDate> rangeEnd, Supplier<LocalDate> insideRange) {
            this.beanPropertyRangeStart = beanPropertyRangeStart;
            this.beanPropertyRangeEnd = beanPropertyRangeEnd;
            this.rangeStart = rangeStart;
            this.rangeEnd = rangeEnd;
            this.insideRange = insideRange;
        }
    }
}

package com.tabit.dcm2.validation;

import java.util.Optional;
import java.util.function.Supplier;

public class DependentlyRequiredValueValidator<T> extends AbstractBeanValidator<DependentlyRequiredValueValidator.DependentlyRequiredValueValidatorInput<T>> {

    private String MESSAGE = "If %s is set to %b, it must be %<b that %s is defined";

    public DependentlyRequiredValueValidator(String beanProperty, Supplier<DependentlyRequiredValueValidatorInput<T>> toValidate) {
        super(beanProperty, toValidate);
    }

    @Override
    public ValidationResult validate() {
        DependentlyRequiredValueValidatorInput<T> validatorInput = this.toValidate.get();


        boolean needTobeRequired = validatorInput.dependeeValue.get();
        boolean isPresentRequiredValue = validatorInput.dependentlyRequiredValue.get().isPresent();

        boolean valid;

        if (needTobeRequired) {
            valid = isPresentRequiredValue;
        } else {
            valid = !isPresentRequiredValue;
        }

        return valid ? ValidationResult.noError() : ValidationResult.withError(
                validatorInput.beanDependeeProperty,
                String.format(
                        MESSAGE,
                        validatorInput.beanDependeeProperty,
                        validatorInput.dependeeValue.get(),
                        validatorInput.beanDependentRequiredProperty
                )
        );

    }

    public static class DependentlyRequiredValueValidatorInput<T> {
        private final String beanDependeeProperty;
        private final String beanDependentRequiredProperty;
        private final Supplier<Boolean> dependeeValue;
        private final Supplier<Optional<T>> dependentlyRequiredValue;

        public DependentlyRequiredValueValidatorInput(String beanDependeeProperty, String beanDependentRequiredProperty, Supplier<Boolean> dependeeValue, Supplier<Optional<T>> dependentlyRequiredValue) {
            this.beanDependeeProperty = beanDependeeProperty;
            this.beanDependentRequiredProperty = beanDependentRequiredProperty;
            this.dependeeValue = dependeeValue;
            this.dependentlyRequiredValue = dependentlyRequiredValue;
        }
    }
}

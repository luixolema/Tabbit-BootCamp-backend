package com.tabit.dcm2.validation;

import java.util.Optional;
import java.util.function.Supplier;

public abstract class AbstractBeanValidator<TO_VALIDATE> implements IBeanValidator {
    protected final Optional<String> beanProperty;
    protected final Supplier<TO_VALIDATE> toValidate;

    protected AbstractBeanValidator(String beanProperty, Supplier<TO_VALIDATE> toValidate) {
        this.beanProperty = Optional.of(beanProperty);
        this.toValidate = toValidate;
    }

    protected AbstractBeanValidator(Supplier<TO_VALIDATE> toValidate) {
        this.beanProperty = Optional.empty();
        this.toValidate = toValidate;
    }
}

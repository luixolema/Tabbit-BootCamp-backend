package com.tabit.dcm2.validation;

import com.tabit.dcm2.commons.AbstractBean;

import java.util.Optional;

public final class ValidationResult extends AbstractBean {
    private final Optional<String> errorMsg;

    private ValidationResult(Optional<String> errorMsg) {
        super();
        this.errorMsg = errorMsg;
    }

    public static ValidationResult withError(String beanProperty, String message) {
        return new ValidationResult(Optional.of(beanProperty + ": " + message));
    }

    public static ValidationResult noError() {
        return new ValidationResult(Optional.empty());
    }

    public Optional<String> getErrorMsg() {
        return errorMsg;
    }
}

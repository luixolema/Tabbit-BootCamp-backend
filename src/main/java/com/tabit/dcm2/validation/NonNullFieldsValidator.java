package com.tabit.dcm2.validation;

import com.tabit.dcm2.commons.AbstractBean;
import com.tabit.dcm2.exception.BeanValidationException;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Supplier;

public class NonNullFieldsValidator implements IBeanValidator {
    private static final String MESSAGE = "Null value is not allowed";

    private final Supplier<AbstractBean> toValidate;

    public NonNullFieldsValidator(Supplier<AbstractBean> toValidate) {
        this.toValidate = toValidate;
    }

    @Override
    public ValidationResult validate() {
        AbstractBean abstractBean = toValidate.get();
        Optional<Field> nullField = Arrays.stream(FieldUtils.getAllFields(abstractBean.getClass())).filter(field -> {
            field.setAccessible(true);
            try {
                return field.get(abstractBean) == null;
            } catch (IllegalAccessException e) {
                throw new BeanValidationException("cannot access fields of " + abstractBean.getClass().getSimpleName(), e);
            }
        }).findFirst();

        return nullField.map(field -> ValidationResult.withError(abstractBean.getClass().getSimpleName() + "." + field.getName(), MESSAGE)).orElseGet(ValidationResult::noError);
    }
}

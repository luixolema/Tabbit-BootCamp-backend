package com.tabit.dcm2.commons;

import com.google.common.collect.Lists;
import com.tabit.dcm2.exception.BeanValidationException;
import com.tabit.dcm2.exception.ProgramException;
import com.tabit.dcm2.validation.IBeanValidator;
import com.tabit.dcm2.validation.NonNullFieldsValidator;
import com.tabit.dcm2.validation.ValidationResult;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public abstract class AbstractNonNullValidatingBeanBuilder<BEAN extends AbstractBean, BUILDER extends AbstractNonNullValidatingBeanBuilder<BEAN, BUILDER>> {

    protected BEAN bean;
    private List<IBeanValidator> beanValidators = Lists.newArrayList(new NonNullFieldsValidator(() -> bean));

    public AbstractNonNullValidatingBeanBuilder(BEAN bean) {
        this.bean = bean;
    }

    private void verifyConsistentSetup() {
        Optional<String> error = beanValidators.stream()
                .map(IBeanValidator::validate)
                .map(ValidationResult::getErrorMsg)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();

        if (error.isPresent()) {
            throw new BeanValidationException(error.get());
        }
    }

    protected BUILDER derivedBuilder() {
        return (BUILDER) this;
    }

    public void addValidators(IBeanValidator... validators) {
        beanValidators.addAll(Arrays.asList(validators));
    }

    public final BEAN build() {
        if (bean == null) {
            throw new ProgramException("Bean was already build");
        }

        verifyConsistentSetup();
        BEAN copy = bean;
        bean = null;
        return copy;
    }
}
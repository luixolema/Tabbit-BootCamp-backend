package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.commons.AbstractNonNullValidatingBeanBuilder;

public class BeanValidationExceptionTestCase<INVALID_DTO_BUILDER extends AbstractNonNullValidatingBeanBuilder<?, ?>> {
    private INVALID_DTO_BUILDER invalidDtoBuilder;
    private String expectedMessage;

    public INVALID_DTO_BUILDER getInvalidDto() {
        return invalidDtoBuilder;
    }

    public String getExpectedMessage() {
        return expectedMessage;
    }

    public BeanValidationExceptionTestCase<INVALID_DTO_BUILDER> withInvalidDto(INVALID_DTO_BUILDER invalidDtoBuilder) {
        this.invalidDtoBuilder = invalidDtoBuilder;
        return this;
    }

    public BeanValidationExceptionTestCase<INVALID_DTO_BUILDER> withExpectedMessage(String expectedMessage) {
        this.expectedMessage = expectedMessage;
        return this;
    }
}

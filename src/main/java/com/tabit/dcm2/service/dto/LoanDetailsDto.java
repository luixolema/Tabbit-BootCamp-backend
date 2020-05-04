package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.commons.AbstractBean;
import com.tabit.dcm2.commons.AbstractNonNullValidatingBeanBuilder;
import com.tabit.dcm2.validation.LocalDateAfterValidator;
import com.tabit.dcm2.validation.ValidationResult;

import java.time.LocalDate;
import java.util.Optional;

@SuppressWarnings("PMD.ImmutableField")
public class LoanDetailsDto extends AbstractBean {
    private Long id;
    private String type;
    private String serialNumber;
    private LocalDate dateOut;
    private Optional<LocalDate> dateReturn = Optional.empty();

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public LocalDate getDateOut() {
        return dateOut;
    }

    public Optional<LocalDate> getDateReturn() {
        return dateReturn;
    }

    public LoanDetailsDto copy() {
        return Builder.builderFromBean(this).build();
    }

    public static class Builder extends AbstractNonNullValidatingBeanBuilder<LoanDetailsDto, LoanDetailsDto.Builder> {

        public Builder() {
            super(new LoanDetailsDto());

            String simpleName = LoanDetailsDto.class.getSimpleName();
            addValidators(
                    () -> bean.getDateReturn().isPresent()
                            ? new LocalDateAfterValidator(simpleName + ".dateReturn", new LocalDateAfterValidator.LocalDateAfterValidatorInput("dateOut", bean::getDateOut, () -> bean.getDateReturn().get())).validate()
                            : ValidationResult.noError()
            );

        }

        public static Builder builderFromBean(LoanDetailsDto toCopy) {
            return new Builder()
                    .withId(toCopy.getId())
                    .withType(toCopy.getType())
                    .withSerialNumber(toCopy.getSerialNumber())
                    .withDateOut(toCopy.getDateOut())
                    .withDateReturn(toCopy.getDateReturn().orElse(null));
        }

        public Builder withId(Long id) {
            bean.id = id;
            return this;
        }

        public Builder withType(String type) {
            bean.type = type;
            return this;
        }

        public Builder withSerialNumber(String serialNumber) {
            bean.serialNumber = serialNumber;
            return this;
        }

        public Builder withDateOut(LocalDate dateOut) {
            bean.dateOut = dateOut;
            return this;
        }

        public Builder withDateReturn(LocalDate dateReturn) {
            bean.dateReturn = Optional.ofNullable(dateReturn);
            return this;
        }
    }
}
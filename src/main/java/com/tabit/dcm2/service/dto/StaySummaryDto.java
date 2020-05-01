package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.commons.AbstractBean;
import com.tabit.dcm2.commons.AbstractNonNullValidatingBeanBuilder;
import com.tabit.dcm2.validation.LocalDateAfterValidator;
import com.tabit.dcm2.validation.LocalDateAfterValidator.LocalDateAfterValidatorInput;
import com.tabit.dcm2.validation.LocalDateNotInThePastValidator;
import com.tabit.dcm2.validation.ValidationResult;

import java.time.LocalDate;

public class StaySummaryDto extends AbstractBean {

    private Long id;
    private LocalDate arriveDate;
    private LocalDate leaveDate;
    private boolean active;

    public Long getId() {
        return id;
    }

    public LocalDate getArriveDate() {
        return arriveDate;
    }

    public LocalDate getLeaveDate() {
        return leaveDate;
    }

    public boolean isActive() {
        return active;
    }

    public static class Builder extends AbstractNonNullValidatingBeanBuilder<StaySummaryDto, StaySummaryDto.Builder> {

        public Builder() {
            super(new StaySummaryDto());
            String simpleName = StaySummaryDto.class.getSimpleName();
            addValidators(
                    new LocalDateAfterValidator(simpleName + ".leaveDate", new LocalDateAfterValidatorInput("arriveDate", bean::getArriveDate, bean::getLeaveDate)),
                    () -> bean.isActive() ? new LocalDateNotInThePastValidator(simpleName + ".leaveDate", bean::getLeaveDate).validate() : ValidationResult.noError()
            );
        }

        public Builder withId(Long id) {
            bean.id = id;
            return this;
        }

        public Builder withArriveDate(LocalDate arriveDate) {
            bean.arriveDate = arriveDate;
            return this;
        }

        public Builder withLeaveDate(LocalDate leaveDate) {
            bean.leaveDate = leaveDate;
            return this;
        }

        public Builder withActive(boolean active) {
            bean.active = active;
            return this;
        }
    }
}
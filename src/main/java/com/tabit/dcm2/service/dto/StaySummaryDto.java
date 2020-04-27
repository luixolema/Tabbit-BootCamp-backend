package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.commons.AbstractBean;
import com.tabit.dcm2.commons.AbstractNonNullValidatingBeanBuilder;
import com.tabit.dcm2.validation.LocalDateAfterValidator;
import com.tabit.dcm2.validation.LocalDateAfterValidator.LocalDateAfterValidatorInput;

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

    public StaySummaryDto copy() {
        return new Builder()
                .builderFromBean(this)
                .build();
    }

    public static class Builder extends AbstractNonNullValidatingBeanBuilder<StaySummaryDto, StaySummaryDto.Builder> {

        public Builder() {
            super(new StaySummaryDto());

            String simpleName = StaySummaryDto.class.getSimpleName();
            addValidators(
                    new LocalDateAfterValidator(simpleName + ".leaveDate", () -> new LocalDateAfterValidatorInput("arriveDate", bean::getArriveDate, bean::getLeaveDate))
            );
        }

        public static Builder builderFromBean(StaySummaryDto toCopy) {
            return new StaySummaryDto.Builder()
                    .withId(toCopy.getId())
                    .withArriveDate(toCopy.getArriveDate())
                    .withLeaveDate(toCopy.getLeaveDate())
                    .withActive(toCopy.isActive());
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
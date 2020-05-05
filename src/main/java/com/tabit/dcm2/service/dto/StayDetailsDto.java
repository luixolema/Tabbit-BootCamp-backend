package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.validation.LocalDateAfterValidator;
import com.tabit.dcm2.validation.LocalDateAfterValidator.LocalDateAfterValidatorInput;
import com.tabit.dcm2.validation.LocalDateInsideRangeValidator;
import com.tabit.dcm2.validation.LocalDateInsideRangeValidator.LocalDateInsideRangeValidatorInput;
import com.tabit.dcm2.validation.LocalDateNotInThePastValidator;
import com.tabit.dcm2.validation.ValidationResult;

import java.time.LocalDate;
import java.util.Optional;

@SuppressWarnings("PMD.ImmutableField")
public class StayDetailsDto extends AbstractStayDetailsDto {
    private Long id;
    private Optional<LocalDate> checkOutDate = Optional.empty();
    private boolean active;

    public Long getId() {
        return id;
    }

    public Optional<LocalDate> getCheckOutDate() {
        return checkOutDate;
    }

    public boolean isActive() {
        return active;
    }

    public StayDetailsDto copy() {
        return Builder.builderFromBean(this).build();
    }

    public static class Builder extends AbstractBuilder<StayDetailsDto, StayDetailsDto.Builder> {

        public Builder() {
            super(new StayDetailsDto());

            String simpleName = StayDetailsDto.class.getSimpleName();
            addValidators(
                    () -> bean.getCheckOutDate().isPresent()
                            ? new LocalDateAfterValidator(simpleName + ".checkOutDate", new LocalDateAfterValidatorInput("checkInDate", bean::getCheckInDate, () -> bean.getCheckOutDate().get())).validate()
                            : ValidationResult.noError(),
                    () -> bean.getCheckOutDate().isPresent()
                            ? new LocalDateInsideRangeValidator(simpleName + ".checkOutDate", new LocalDateInsideRangeValidatorInput("arriveDate", "leaveDate", bean::getArriveDate, bean::getLeaveDate, () -> bean.getCheckOutDate().get())).validate()
                            : ValidationResult.noError(),
                    () -> bean.isActive() ? new LocalDateNotInThePastValidator(simpleName + ".leaveDate", bean::getLeaveDate).validate() : ValidationResult.noError()
            );
        }

        public static Builder builderFromBean(StayDetailsDto toCopy) {
            return new Builder()
                    .withId(toCopy.getId())
                    .withBoxNumber(toCopy.getBoxNumber())
                    .withCheckInDate(toCopy.getCheckInDate())
                    .withCheckOutDate(toCopy.getCheckOutDate().orElse(null))
                    .withArriveDate(toCopy.getArriveDate())
                    .withLeaveDate(toCopy.getLeaveDate())
                    .withHotel(toCopy.getHotel())
                    .withRoom(toCopy.getRoom())
                    .withLastDiveDate(toCopy.getLastDiveDate())
                    .withBrevet(toCopy.getBrevet())
                    .withDivesAmount(toCopy.getDivesAmount())
                    .withNitrox(toCopy.isNitrox())
                    .withMedicalStatement(toCopy.isMedicalStatement())
                    .withPreBooking(toCopy.getPreBooking())
                    .withActive(toCopy.isActive());
        }

        public Builder withId(Long id) {
            bean.id = id;
            return this;
        }

        public Builder withBoxNumber(String boxNumber) {
            bean.boxNumber = boxNumber;
            return this;
        }

        public Builder withCheckInDate(LocalDate checkInDate) {
            bean.checkInDate = checkInDate;
            return this;
        }

        public Builder withCheckOutDate(LocalDate checkOutDate) {
            bean.checkOutDate = Optional.ofNullable(checkOutDate);
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

        public Builder withHotel(String hotel) {
            bean.hotel = hotel;
            return this;
        }

        public Builder withRoom(String room) {
            bean.room = room;
            return this;
        }

        public Builder withLastDiveDate(LocalDate lastDiveDate) {
            bean.lastDiveDate = lastDiveDate;
            return this;
        }

        public Builder withBrevet(String brevet) {
            bean.brevet = brevet;
            return this;
        }

        public Builder withDivesAmount(Integer divesAmount) {
            bean.divesAmount = divesAmount;
            return this;
        }

        public Builder withNitrox(boolean nitrox) {
            bean.nitrox = nitrox;
            return this;
        }

        public Builder withMedicalStatement(boolean medicalStatement) {
            bean.medicalStatement = medicalStatement;
            return this;
        }

        public Builder withPreBooking(String preBooking) {
            bean.preBooking = preBooking;
            return this;
        }

        public Builder withActive(boolean active) {
            bean.active = active;
            return this;
        }
    }
}
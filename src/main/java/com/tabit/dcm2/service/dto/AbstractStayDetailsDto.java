package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.commons.AbstractBean;
import com.tabit.dcm2.commons.AbstractNonNullValidatingBeanBuilder;
import com.tabit.dcm2.validation.LocalDateAfterValidator;
import com.tabit.dcm2.validation.LocalDateAfterValidator.LocalDateAfterValidatorInput;
import com.tabit.dcm2.validation.LocalDateInsideRangeValidator;
import com.tabit.dcm2.validation.LocalDateInsideRangeValidator.LocalDateInsideRangeValidatorInput;
import com.tabit.dcm2.validation.LocalDateNotInTheFutureValidator;
import com.tabit.dcm2.validation.PositiveIntegerValidator;

import java.time.LocalDate;

public abstract class AbstractStayDetailsDto extends AbstractBean {
    protected String boxNumber;
    protected LocalDate checkInDate;
    protected LocalDate arriveDate;
    protected LocalDate leaveDate;
    protected String hotel;
    protected String room;
    protected LocalDate lastDiveDate;
    protected String brevet;
    protected Integer divesAmount;
    protected boolean nitrox;
    protected boolean medicalStatement;
    protected String preBooking;

    public String getBoxNumber() {
        return boxNumber;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getArriveDate() {
        return arriveDate;
    }

    public LocalDate getLeaveDate() {
        return leaveDate;
    }

    public String getHotel() {
        return hotel;
    }

    public String getRoom() {
        return room;
    }

    public LocalDate getLastDiveDate() {
        return lastDiveDate;
    }

    public String getBrevet() {
        return brevet;
    }

    public Integer getDivesAmount() {
        return divesAmount;
    }

    public boolean isNitrox() {
        return nitrox;
    }

    public boolean isMedicalStatement() {
        return medicalStatement;
    }

    public String getPreBooking() {
        return preBooking;
    }

    protected abstract static class AbstractBuilder<BEAN extends AbstractStayDetailsDto, BUILDER extends AbstractBuilder<BEAN, BUILDER>> extends AbstractNonNullValidatingBeanBuilder<BEAN, BUILDER> {

        protected AbstractBuilder(BEAN bean) {
            super(bean);

            String simpleName = AbstractStayDetailsDto.class.getSimpleName();
            addValidators(
                    new PositiveIntegerValidator(simpleName + ".divesAmount", bean::getDivesAmount),
                    new LocalDateAfterValidator(simpleName + ".leaveDate", () -> new LocalDateAfterValidatorInput("arriveDate", bean::getArriveDate, bean::getLeaveDate)),
                    new LocalDateInsideRangeValidator(simpleName + ".checkInDate", () -> new LocalDateInsideRangeValidatorInput("arriveDate", "leaveDate", bean::getArriveDate, bean::getLeaveDate, bean::getCheckInDate)),
                    new LocalDateNotInTheFutureValidator(simpleName + ".lastDiveDate", bean::getLastDiveDate)
            );
        }

        public BUILDER withBoxNumber(String boxNumber) {
            bean.boxNumber = boxNumber;
            return derivedBuilder();
        }

        public BUILDER withCheckInDate(LocalDate checkInDate) {
            bean.checkInDate = checkInDate;
            return derivedBuilder();
        }

        public BUILDER withArriveDate(LocalDate arriveDate) {
            bean.arriveDate = arriveDate;
            return derivedBuilder();
        }

        public BUILDER withLeaveDate(LocalDate leaveDate) {
            bean.leaveDate = leaveDate;
            return derivedBuilder();
        }

        public BUILDER withHotel(String hotel) {
            bean.hotel = hotel;
            return derivedBuilder();
        }

        public BUILDER withRoom(String room) {
            bean.room = room;
            return derivedBuilder();
        }

        public BUILDER withLastDiveDate(LocalDate lastDiveDate) {
            bean.lastDiveDate = lastDiveDate;
            return derivedBuilder();
        }

        public BUILDER withBrevet(String brevet) {
            bean.brevet = brevet;
            return derivedBuilder();
        }

        public BUILDER withDivesAmount(Integer divesAmount) {
            bean.divesAmount = divesAmount;
            return derivedBuilder();
        }

        public BUILDER withNitrox(boolean nitrox) {
            bean.nitrox = nitrox;
            return derivedBuilder();
        }

        public BUILDER withMedicalStatement(boolean medicalStatement) {
            bean.medicalStatement = medicalStatement;
            return derivedBuilder();
        }

        public BUILDER withPreBooking(String preBooking) {
            bean.preBooking = preBooking;
            return derivedBuilder();
        }
    }
}

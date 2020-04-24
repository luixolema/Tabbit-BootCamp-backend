package com.tabit.dcm2.service.dto;

import java.time.LocalDate;
import java.util.Optional;

public class StayDetailsDto extends AbstractStayDetailsDto {
    private Long id;
    private Optional<LocalDate> checkOutDate = Optional.empty();
    private boolean active;

    public void setBoxNumber(String boxNumber) {
        this.boxNumber = boxNumber;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setArriveDate(LocalDate arriveDate) {
        this.arriveDate = arriveDate;
    }

    public void setLeaveDate(LocalDate leaveDate) {
        this.leaveDate = leaveDate;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setLastDiveDate(LocalDate lastDiveDate) {
        this.lastDiveDate = lastDiveDate;
    }

    public void setBrevet(String brevet) {
        this.brevet = brevet;
    }

    public void setDivesAmount(Integer divesAmount) {
        this.divesAmount = divesAmount;
    }

    public void setNitrox(boolean nitrox) {
        this.nitrox = nitrox;
    }

    public void setMedicalStatement(boolean medicalStatement) {
        this.medicalStatement = medicalStatement;
    }

    public void setPreBooking(String preBooking) {
        this.preBooking = preBooking;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Optional<LocalDate> getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = Optional.ofNullable(checkOutDate);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
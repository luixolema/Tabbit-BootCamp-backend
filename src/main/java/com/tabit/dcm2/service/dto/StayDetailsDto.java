package com.tabit.dcm2.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class StayDetailsDto {
    private String boxNumber;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate checkInDate;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate checkOutDate;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate arriveDate;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate leaveDate;
    private String hotel;
    private String room;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate lastDiveDate;
    private String brevet;
    private Integer divesAmount;
    private boolean nitrox;
    private boolean medicalStatement;
    private String preBoocking;

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public LocalDate getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(LocalDate arriveDate) {
        this.arriveDate = arriveDate;
    }

    public LocalDate getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(LocalDate leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public LocalDate getLastDiveDate() {
        return lastDiveDate;
    }

    public void setLastDiveDate(LocalDate lastDiveDate) {
        this.lastDiveDate = lastDiveDate;
    }

    public String getBrevet() {
        return brevet;
    }

    public void setBrevet(String brevet) {
        this.brevet = brevet;
    }

    public Integer getDivesAmount() {
        return divesAmount;
    }

    public void setDivesAmount(Integer divesAmount) {
        this.divesAmount = divesAmount;
    }

    public boolean isNitrox() {
        return nitrox;
    }

    public void setNitrox(boolean nitrox) {
        this.nitrox = nitrox;
    }

    public boolean isMedicalStatement() {
        return medicalStatement;
    }

    public void setMedicalStatement(boolean medicalStatement) {
        this.medicalStatement = medicalStatement;
    }

    public String getBoxNumber() {
        return boxNumber;
    }

    public void setBoxNumber(String boxNumber) {
        this.boxNumber = boxNumber;
    }

    public String getPreBoocking() {
        return preBoocking;
    }

    public void setPreBoocking(String preBoocking) {
        this.preBoocking = preBoocking;
    }
}
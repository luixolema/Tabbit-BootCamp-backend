package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.entity.Payment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDto {
    private String invoiceId;
    private String firstName;
    private String lastName;
    private String city;
    private String postcode;
    private String street;
    private String country;
    private LocalDate arriveDate;
    private LocalDate leaveDate;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String box;
    private List<PaymentDetailsDto> payments = new ArrayList<>();
    private Long total;

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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

    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = box;
    }

    public List<PaymentDetailsDto> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentDetailsDto> payments) {
        this.payments = payments;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void addPaymentDetail(Payment payment) {
        this.payments.add(new PaymentDetailsDto(payment.getActivityType(), payment.getActivityDate(), payment.getPrice()));
    }
}

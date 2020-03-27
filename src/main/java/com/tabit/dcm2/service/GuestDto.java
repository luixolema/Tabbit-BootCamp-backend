package com.tabit.dcm2.service;

import java.util.Date;

public class GuestDto {

    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String nationality;
    private String country;
    private String city;
    private String postcode;
    private String street;
    private String email;
    private String phone;
    private String passportId;
    private Date lastDive;
    private String brevet;
    private Integer divesAmount;
    private boolean nitrox;
    private boolean medicalStatement;
    private boolean checkedin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public Date getLastDive() {
        return lastDive;
    }

    public void setLastDive(Date lastDive) {
        this.lastDive = lastDive;
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

    public boolean isCheckedin() {
        return checkedin;
    }

    public void setCheckedin(boolean checkedin) {
        this.checkedin = checkedin;
    }
}

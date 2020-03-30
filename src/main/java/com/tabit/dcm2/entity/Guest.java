package com.tabit.dcm2.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Guest implements IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "guest", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy("check_in_date DESC")
    List<Stay> stays = new ArrayList<>();

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "nationality", nullable = false)
    private String nationality;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "postcode", nullable = false)
    private String postcode;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "passport_id", nullable = false)
    private String passportId;

    @Column(name = "last_dive_date", nullable = false)
    private LocalDate lastDiveDate;

    @Column(name = "brevet", nullable = false)
    private String brevet;

    @Column(name = "dives_amount", nullable = false)
    private Integer divesAmount;

    @Column(name = "nitrox", nullable = false)
    private boolean nitrox;

    @Column(name = "medical_statement", nullable = false)
    private boolean medicalStatement;

    @Column(name = "checked_in")
    private boolean checkedin;

    @Override
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
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

    public boolean isCheckedin() {
        return checkedin;
    }

    public void setCheckedin(boolean checkedin) {
        this.checkedin = checkedin;
    }

    public List<Stay> getStays() {
        return stays;
    }

    public void setStays(List<Stay> stays) {
        this.stays = stays;
    }

    public void addStays(List<Stay> stays) {
        setStays(stays);
        stays.stream().forEach(s -> s.setGuest(this));
    }
}
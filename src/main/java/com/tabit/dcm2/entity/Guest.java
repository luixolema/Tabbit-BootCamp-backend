package com.tabit.dcm2.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Guest implements IEntity {
    @Id
    @Column(name = "id")
    @TableGenerator(allocationSize = 1,
            name = "IDGenerator",
            table = "id_gen",
            pkColumnName = "id_name",
            valueColumnName = "id_value",
            pkColumnValue = "guest_id")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "IDGenerator")
    private Long id;

    @OneToMany(mappedBy = "guest", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @OrderBy("arrive_date DESC")
    List<Stay> stays = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guest guest = (Guest) o;
        return isCheckedin() == guest.isCheckedin() &&
                Objects.equals(getId(), guest.getId()) &&
                Objects.equals(getFirstName(), guest.getFirstName()) &&
                Objects.equals(getLastName(), guest.getLastName()) &&
                Objects.equals(getBirthDate(), guest.getBirthDate()) &&
                Objects.equals(getNationality(), guest.getNationality()) &&
                Objects.equals(getCountry(), guest.getCountry()) &&
                Objects.equals(getCity(), guest.getCity()) &&
                Objects.equals(getPostcode(), guest.getPostcode()) &&
                Objects.equals(getStreet(), guest.getStreet()) &&
                Objects.equals(getEmail(), guest.getEmail()) &&
                Objects.equals(getPhone(), guest.getPhone()) &&
                Objects.equals(getPassportId(), guest.getPassportId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getBirthDate(), getNationality(), getCountry(), getCity(), getPostcode(), getStreet(), getEmail(), getPhone(), getPassportId(), isCheckedin());
    }

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

    @Column(name = "email")
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "passport_id", nullable = false)
    private String passportId;

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
        stays.forEach(s -> s.setGuest(this));
    }
}
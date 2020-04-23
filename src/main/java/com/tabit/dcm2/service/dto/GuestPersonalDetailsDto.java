package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.commons.AbstractBean;
import com.tabit.dcm2.commons.AbstractNonNullValidatingBeanBuilder;
import com.tabit.dcm2.validation.EmailValidator;

import java.time.LocalDate;

public class GuestPersonalDetailsDto extends AbstractBean {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String nationality;
    private String country;
    private String city;
    private String postcode;
    private String street;
    private String email;
    private String phone;
    private String passportId;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getNationality() {
        return nationality;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getStreet() {
        return street;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassportId() {
        return passportId;
    }

    public GuestPersonalDetailsDto copy() {
        return Builder.builderFromBean(this).build();
    }

    public static class Builder extends AbstractNonNullValidatingBeanBuilder<GuestPersonalDetailsDto, Builder> {

        public Builder() {
            super(new GuestPersonalDetailsDto());
            addValidators(new EmailValidator(GuestPersonalDetailsDto.class.getSimpleName() + ".email", bean::getEmail));
        }

        public static Builder builderFromBean(GuestPersonalDetailsDto toCopy) {
            return new Builder()
                    .withId(toCopy.getId())
                    .withFirstName(toCopy.getFirstName())
                    .withLastName(toCopy.getLastName())
                    .withBirthDate(toCopy.getBirthDate())
                    .withEmail(toCopy.getEmail())
                    .withNationality(toCopy.getNationality())
                    .withCity(toCopy.getCity())
                    .withPassportId(toCopy.getPassportId())
                    .withPhone(toCopy.getPhone())
                    .withCountry(toCopy.getCountry())
                    .withPostcode(toCopy.getPostcode())
                    .withStreet(toCopy.getStreet());
        }

        public Builder withId(Long id) {
            bean.id = id;
            return this;
        }

        public Builder withFirstName(String firstName) {
            bean.firstName = firstName;
            return this;
        }


        public Builder withLastName(String lastName) {
            bean.lastName = lastName;
            return this;
        }

        public Builder withBirthDate(LocalDate birthDate) {
            bean.birthDate = birthDate;
            return this;
        }

        public Builder withNationality(String nationality) {
            bean.nationality = nationality;
            return this;
        }

        public Builder withCountry(String country) {
            bean.country = country;
            return this;
        }

        public Builder withCity(String city) {
            bean.city = city;
            return this;
        }

        public Builder withPostcode(String postcode) {
            bean.postcode = postcode;
            return this;
        }

        public Builder withStreet(String street) {
            bean.street = street;
            return this;
        }

        public Builder withEmail(String email) {
            bean.email = email;
            return this;
        }

        public Builder withPhone(String phone) {
            bean.phone = phone;
            return this;
        }

        public Builder withPassportId(String passportId) {
            bean.passportId = passportId;
            return this;
        }
    }
}
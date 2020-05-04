package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.commons.AbstractBean;
import com.tabit.dcm2.commons.AbstractNonNullValidatingBeanBuilder;
import com.tabit.dcm2.validation.EmailValidator;
import com.tabit.dcm2.validation.LocalDateNotInTheFutureValidator;

import java.time.LocalDate;
import java.util.Optional;

public abstract class AbstractGuestPersonalDetailsDto extends AbstractBean {
    protected String firstName;
    protected String lastName;
    protected LocalDate birthDate;
    protected String nationality;
    protected String country;
    protected String city;
    protected String postcode;
    protected String street;
    protected String email;
    protected Optional<String> phone;
    protected Optional<String> passportId;

    protected static abstract class AbstractBuilder<BEAN extends AbstractGuestPersonalDetailsDto, BUILDER extends AbstractBuilder<BEAN, BUILDER>> extends AbstractNonNullValidatingBeanBuilder<BEAN, BUILDER> {

        protected AbstractBuilder(BEAN bean) {
            super(bean);

            String simpleName = AbstractGuestPersonalDetailsDto.class.getSimpleName();
            addValidators(
                    new EmailValidator(simpleName + ".email", bean::getEmail),
                    new LocalDateNotInTheFutureValidator(simpleName + ".birthDate", bean::getBirthDate)
            );
        }

        public BUILDER withFirstName(String firstName) {
            bean.firstName = firstName;
            return derivedBuilder();
        }

        public BUILDER withLastName(String lastName) {
            bean.lastName = lastName;
            return derivedBuilder();
        }

        public BUILDER withBirthDate(LocalDate birthday) {
            bean.birthDate = birthday;
            return derivedBuilder();
        }

        public BUILDER withCity(String city) {
            bean.city = city;
            return derivedBuilder();
        }

        public BUILDER withCountry(String country) {
            bean.country = country;
            return derivedBuilder();
        }

        public BUILDER withEmail(String email) {
            bean.email = email;
            return derivedBuilder();
        }

        public BUILDER withNationality(String nationality) {
            bean.nationality = nationality;
            return derivedBuilder();
        }

        public BUILDER withPhone(String phone) {
            bean.phone = Optional.ofNullable(phone);
            return derivedBuilder();
        }

        public BUILDER withStreet(String street) {
            bean.street = street;
            return derivedBuilder();
        }

        public BUILDER withPostcode(String postCode) {
            bean.postcode = postCode;
            return derivedBuilder();
        }

        public BUILDER withPassportId(String passportId) {
            bean.passportId = Optional.ofNullable(passportId);
            return derivedBuilder();
        }
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

    public Optional<String> getPhone() {
        return phone;
    }

    public Optional<String> getPassportId() {
        return passportId;
    }
}
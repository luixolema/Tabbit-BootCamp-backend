package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.validation.EmailValidator;


public class GuestPersonalDetailsDto extends AbstractGuestPersonalDetailsDto {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GuestPersonalDetailsDto copy() {
        return Builder.builderFromBean(this).build();
    }

    public static class Builder extends AbstractGuestPersonalDetailsDto.AbstractBuilder<GuestPersonalDetailsDto, Builder> {

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
    }

}
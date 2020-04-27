package com.tabit.dcm2.service.dto;

public class GuestCreationDto extends AbstractGuestPersonalDetailsDto {

    public GuestCreationDto copy() {
        return Builder.builderFromBean(this).build();
    }

    public static class Builder extends AbstractBuilder<GuestCreationDto, Builder> {

        public Builder() {
            super(new GuestCreationDto());
        }

        public static Builder builderFromBean(GuestCreationDto toCopy) {
            return new Builder()
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
    }

}
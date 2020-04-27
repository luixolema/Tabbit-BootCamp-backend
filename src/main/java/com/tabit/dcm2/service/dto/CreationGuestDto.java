package com.tabit.dcm2.service.dto;

public class CreationGuestDto extends AbstractGuestPersonalDetailsDto {

    public CreationGuestDto copy() {
        return Builder.builderFromBean(this).build();
    }

    public static class Builder extends AbstractGuestPersonalDetailsDto.AbstractBuilder<CreationGuestDto, Builder> {

        public Builder() {
            super(new CreationGuestDto());
        }

        public static Builder builderFromBean(CreationGuestDto toCopy) {
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
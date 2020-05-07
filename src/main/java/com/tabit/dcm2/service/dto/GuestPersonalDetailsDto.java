package com.tabit.dcm2.service.dto;

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

    public static class Builder extends AbstractBuilder<GuestPersonalDetailsDto, Builder> {

        public Builder() {
            super(new GuestPersonalDetailsDto());
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
                    .withPassportId(toCopy.getPassportId().orElse(null))
                    .withPhone(toCopy.getPhone().orElse(null))
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
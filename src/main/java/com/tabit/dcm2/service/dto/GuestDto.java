package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.commons.AbstractBean;
import com.tabit.dcm2.commons.AbstractNonNullValidatingBeanBuilder;
import com.tabit.dcm2.validation.DependentlyRequiredValueValidator;

import java.util.Optional;

@SuppressWarnings("PMD.ImmutableField")
public class GuestDto extends AbstractBean {
    private Long id;
    private String firstName;
    private String lastName;
    private Optional<String> boxNumber = Optional.empty();

    private boolean checkedin;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Optional<String> getBoxNumber() {
        return boxNumber;
    }

    public boolean isCheckedin() {
        return checkedin;
    }

    public static class Builder extends AbstractNonNullValidatingBeanBuilder<GuestDto, GuestDto.Builder> {

        public Builder() {
            super(new GuestDto());

            String simpleName = GuestDto.class.getSimpleName();
            addValidators(
                    new DependentlyRequiredValueValidator<>(
                            simpleName + ".boxNumber",
                            () -> new DependentlyRequiredValueValidator.DependentlyRequiredValueValidatorInput<>(
                                    "CheckIn",
                                    "BoxNumber",
                                    bean::isCheckedin,
                                    bean::getBoxNumber
                            )
                    )
            );
        }

        public GuestDto.Builder withId(Long id) {
            bean.id = id;
            return this;
        }

        public GuestDto.Builder withFirstName(String firstName) {
            bean.firstName = firstName;
            return this;
        }

        public GuestDto.Builder withLastName(String lastName) {
            bean.lastName = lastName;
            return this;
        }

        public GuestDto.Builder withBoxNumber(String boxNumber) {
            bean.boxNumber = Optional.ofNullable(boxNumber);
            return this;
        }

        public GuestDto.Builder withCheckedin(boolean checkedin) {
            bean.checkedin = checkedin;
            return this;
        }
    }
}

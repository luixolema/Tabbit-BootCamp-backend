package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.validation.LocalDateNotInThePastValidator;

public class StayDetailsForCheckInDto extends AbstractStayDetailsDto {

    public StayDetailsForCheckInDto copy() {
        return Builder.builderFromBean(this).build();
    }

    public static class Builder extends AbstractBuilder<StayDetailsForCheckInDto, Builder> {

        public Builder() {
            super(new StayDetailsForCheckInDto());

            String simpleName = StayDetailsForCheckInDto.class.getSimpleName();
            addValidators(
                    new LocalDateNotInThePastValidator(simpleName + ".leaveDate", bean::getLeaveDate)
            );
        }

        public static Builder builderFromBean(StayDetailsForCheckInDto toCopy) {
            return new Builder()
                    .withArriveDate(toCopy.getArriveDate())
                    .withBoxNumber(toCopy.getBoxNumber())
                    .withBrevet(toCopy.getBrevet())
                    .withCheckInDate(toCopy.getCheckInDate())
                    .withDivesAmount(toCopy.getDivesAmount())
                    .withHotel(toCopy.getHotel())
                    .withLastDiveDate(toCopy.getLastDiveDate())
                    .withLeaveDate(toCopy.getLeaveDate())
                    .withMedicalStatement(toCopy.isMedicalStatement())
                    .withNitrox(toCopy.isNitrox())
                    .withPreBooking(toCopy.getPreBooking())
                    .withRoom(toCopy.getRoom());
        }
    }
}
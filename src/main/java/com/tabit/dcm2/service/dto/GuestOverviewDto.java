package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.commons.AbstractBean;
import com.tabit.dcm2.commons.AbstractNonNullValidatingBeanBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GuestOverviewDto extends AbstractBean {
    private List<GuestDto> guests = new ArrayList<>();
    private int total;

    // default Costructor needed for json
    public GuestOverviewDto() {
    }

    public GuestOverviewDto(List<GuestDto> guests) {
        this.guests = guests;
        this.total = guests.size();
    }

    public List<GuestDto> getGuests() {
        return guests;
    }

    public int getTotal() {
        return guests.size();
    }


    public GuestOverviewDto copy() {
        return new Builder()
                .withGuests(getGuests().stream().map(GuestDto::copy).collect(Collectors.toList()))
                .build();
    }

    public static class Builder extends AbstractNonNullValidatingBeanBuilder<GuestOverviewDto, Builder> {

        public Builder() {
            super(new GuestOverviewDto());
        }

        public Builder withGuests(List<GuestDto> guests) {
            bean.guests = guests;
            return this;
        }
    }

}

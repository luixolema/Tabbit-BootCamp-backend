package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.commons.AbstractBean;
import com.tabit.dcm2.commons.AbstractNonNullValidatingBeanBuilder;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("PMD.ImmutableField")
public class GuestOverviewDto extends AbstractBean {
    private List<GuestDto> guests = new ArrayList<>();
    private int total;

    public List<GuestDto> getGuests() {
        return guests;
    }

    public int getTotal() {
        return total;
    }

    public static class Builder extends AbstractNonNullValidatingBeanBuilder<GuestOverviewDto, Builder> {

        public Builder() {
            super(new GuestOverviewDto());
        }

        public Builder withGuests(List<GuestDto> guests) {
            bean.guests = guests;
            bean.total = guests.size();
            return this;
        }
    }

}

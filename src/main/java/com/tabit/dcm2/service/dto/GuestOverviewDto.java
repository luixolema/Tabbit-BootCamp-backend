package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.commons.AbstractBean;

import java.util.List;

public class GuestOverviewDto extends AbstractBean {
    private List<GuestDto> guests;
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
        return total;
    }
}

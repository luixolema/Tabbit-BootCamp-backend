package com.tabit.dcm2.service;

import java.util.List;

public class GuestsDto {
    private List<GuestsDto> guests;
    private int total = 0;

    public List<GuestsDto> getGuests() {
        return guests;
    }

    public void setGuests(List<GuestsDto> guests) {
        this.guests = guests;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

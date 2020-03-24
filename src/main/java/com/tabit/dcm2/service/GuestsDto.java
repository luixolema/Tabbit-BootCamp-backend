package com.tabit.dcm2.service;

import java.util.List;

public class GuestsDto {
    private List<GuestDto> guests;
    private int total;

    public GuestsDto(List<GuestDto> guests) {
        this.guests = guests;
        this.total = guests.size();
    }

    public List<GuestDto> getGuests() {
        return guests;
    }

    // TODO in ControllerTask: if we do not need the setter for json, then remove it
    public void setGuests(List<GuestDto> guests) {
        this.guests = guests;
        this.total = guests.size();
    }

    public int getTotal() {
        return total;
    }

    // TODO in ControllerTask: if we do not need the setter for json, then remove it
    public void setTotal(int total) {
        this.total = total;
    }
}

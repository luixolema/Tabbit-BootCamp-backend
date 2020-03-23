package com.tabit.dcm2.service;

import java.util.List;

public interface IGuestService {
    List<GuestDto> getGuests(Boolean checkedIn);
}

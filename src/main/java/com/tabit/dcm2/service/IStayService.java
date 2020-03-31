package com.tabit.dcm2.service;

import com.tabit.dcm2.entity.Stay;

import java.util.List;

public interface IStayService {
    Stay findById(Long stayId);

    List<Stay> findByGuestIdOrderByCheckInDateDesc(Long guestId);
}

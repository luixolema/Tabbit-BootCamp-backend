package com.tabit.dcm2.service;

import com.tabit.dcm2.entity.Stay;

public interface IStayService {
    Stay findById(Long stayId);

    void updateStay(Stay stay);
}

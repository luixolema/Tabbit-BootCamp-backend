package com.tabit.dcm2.service;

import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.service.dto.StayDto;

public interface IStayService {
    Stay findById(Long stayId);

    void updateStay(StayDto stayDto);

    Boolean isBoxEmpty(String boxNumber);
}

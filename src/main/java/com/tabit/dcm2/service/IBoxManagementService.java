package com.tabit.dcm2.service;

import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.service.dto.StayDto;

public interface IBoxManagementService {
    Boolean isBoxFree(String boxNumber);
}

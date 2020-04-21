package com.tabit.dcm2.service.impl;

import com.tabit.dcm2.entity.BoxManagement;
import com.tabit.dcm2.repository.IBoxManagementRepo;
import com.tabit.dcm2.service.IBoxManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoxManagementService implements IBoxManagementService {

    @Autowired
    private IBoxManagementRepo boxManagementRepo;

    @Override
    public Boolean isBoxFree(String boxNumber) {
        Optional<BoxManagement> boxManagement = boxManagementRepo.findByBoxNumber(boxNumber);
        return !boxManagement.isPresent();
    }
}
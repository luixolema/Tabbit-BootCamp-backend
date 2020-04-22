package com.tabit.dcm2.service.impl;

import com.tabit.dcm2.entity.BoxManagement;
import com.tabit.dcm2.exception.BoxReservationException;
import com.tabit.dcm2.repository.IBoxManagementRepo;
import com.tabit.dcm2.service.IBoxManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    @Override
    public void reserveBox(String boxNumber) {
        BoxManagement boxManagement1 = new BoxManagement();
        boxManagement1.setBoxNumber(boxNumber);

        try {
            boxManagementRepo.save(boxManagement1);
        } catch (DataIntegrityViolationException ex) {
            throw new BoxReservationException();
        }
    }
}
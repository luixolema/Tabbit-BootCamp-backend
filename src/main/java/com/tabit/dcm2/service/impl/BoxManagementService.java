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

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public Boolean isBoxFree(String boxNumber) {
        Long diveCenterId = authenticationService.getLoggedInUser().getDiveCenter().getId();
        Optional<BoxManagement> boxManagement = boxManagementRepo.findOneByBoxNumberAndDiveCenterId(boxNumber, diveCenterId);
        return !boxManagement.isPresent();
    }

    @Override
    public void reserveBox(String boxNumber) {
        BoxManagement boxManagement = new BoxManagement();
        boxManagement.setBoxNumber(boxNumber);
        boxManagement.setDiveCenter(authenticationService.getLoggedInUser().getDiveCenter());

        try {
            boxManagementRepo.save(boxManagement);
        } catch (DataIntegrityViolationException ex) {
            throw new BoxReservationException(ex);
        }
    }

    @Override
    public void releaseBox(String boxNumber) {
        Long diveCenterId = authenticationService.getLoggedInUser().getDiveCenter().getId();
        Optional<BoxManagement> boxManagement = boxManagementRepo.findOneByBoxNumberAndDiveCenterId(boxNumber, diveCenterId);
        boxManagement.ifPresent(management -> boxManagementRepo.delete(management));
    }
}
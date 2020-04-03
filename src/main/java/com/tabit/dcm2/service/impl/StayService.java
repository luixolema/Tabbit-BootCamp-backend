package com.tabit.dcm2.service.impl;

import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.exception.ResourceNotFoundException;
import com.tabit.dcm2.repository.IStayRepo;
import com.tabit.dcm2.service.IStayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StayService implements IStayService {
    @Autowired
    private IStayRepo stayRepo;

    @Override
    public Stay findById(Long stayId) {
        Optional<Stay> stay = stayRepo.findById(stayId);
        return stay.orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void updateStay(Stay stay) {
        stayRepo.saveAndFlush(stay);
    }
}
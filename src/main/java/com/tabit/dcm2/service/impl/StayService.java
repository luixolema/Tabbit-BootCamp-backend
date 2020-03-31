package com.tabit.dcm2.service.impl;

import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.exception.ResourceNotFoundException;
import com.tabit.dcm2.repository.IStayRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StayService {
    @Autowired
    private IStayRepo stayRepo;

    public Stay findById(Long stayId) {
        Optional<Stay> stay = stayRepo.findById(stayId);
        return stay.orElseThrow(ResourceNotFoundException::new);
    }

    public List<Stay> findByGuestIdOrderByCheckInDateDesc(Long guestId) {
        return stayRepo.findByGuestIdOrderByCheckInDateDesc(guestId);
    }
}
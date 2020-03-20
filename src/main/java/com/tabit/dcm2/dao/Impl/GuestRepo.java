package com.tabit.dcm2.dao.Impl;

import com.tabit.dcm2.dao.IGuestRepo;
import com.tabit.dcm2.entity.Guest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class GuestRepo implements IGuestRepo {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Guest getGuest(Long guestId) {
        return null;
    }

    @Override
    public List<Guest> getGuests() {
        Query query = entityManager.createNativeQuery("select * from GUEST", Guest.class);
        return query.getResultList();
    }
}
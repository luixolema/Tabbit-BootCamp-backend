package com.tabit.dcm2.entity;

import javax.persistence.*;

@Entity
public class Box implements IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
//  need to check the column name of the id of the guest
//  @JoinColumn(name = "guest_id")
    private Guest guest;

    @Override
    public long getId() {
        return id;
    }
}

package com.tabit.dcm2.entity;

import javax.persistence.*;

@Entity
public class Box {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
//  need to check the column name of the id of the guest
//  @JoinColumn(name = "guest_id")
    private Guest guest;
}

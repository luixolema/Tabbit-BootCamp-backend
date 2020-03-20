package com.tabit.dcm2.entity;


import javax.persistence.*;

@Entity
public class Guest implements IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private long boxId;


    @Override
    public long getId() {
        return id;
    }
}
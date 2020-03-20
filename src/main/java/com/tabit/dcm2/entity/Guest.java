package com.tabit.dcm2.entity;


import javax.persistence.*;

@Entity
public class Guest implements IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String lastName;

    @OneToOne(mappedBy = "guest", cascade = CascadeType.ALL)
    private Box box;

    @Column(name = "checked_in")
    private boolean checkedIn;

    public Guest() {
    }

    public Guest(String firstName, String lastName, Box box, boolean checkedIn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.box = box;
        this.checkedIn = checkedIn;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public void addBox(Box box) {
        box.setGuest(this);
        this.box = box;
    }

    public boolean isCheckIn() {
        return checkedIn;
    }

    public void setCheckIn(boolean checkIn) {
        this.checkedIn = checkIn;
    }
}
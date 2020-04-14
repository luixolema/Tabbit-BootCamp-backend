package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.commons.AbstractBean;

import java.util.Objects;

public class GuestDto extends AbstractBean {
    private Long id;
    private String firstName;
    private String lastName;
    private String boxNumber;
    private boolean checkedin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public boolean isCheckedin() {
        return checkedin;
    }

    public void setCheckedin(boolean checkedin) {
        this.checkedin = checkedin;
    }

    public String getBoxNumber() {
        return boxNumber;
    }

    public void setBoxNumber(String boxNumber) {
        this.boxNumber = boxNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuestDto guestDto = (GuestDto) o;
        return isCheckedin() == guestDto.isCheckedin() &&
                getId().equals(guestDto.getId()) &&
                Objects.equals(getFirstName(), guestDto.getFirstName()) &&
                Objects.equals(getLastName(), guestDto.getLastName()) &&
                Objects.equals(getBoxNumber(), guestDto.getBoxNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getBoxNumber(), isCheckedin());
    }

    @Override
    public String toString() {
        return "GuestDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", boxNumber='" + boxNumber + '\'' +
                ", checkedin=" + checkedin +
                '}';
    }
}

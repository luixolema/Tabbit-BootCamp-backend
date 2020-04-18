package com.tabit.dcm2.service.dto;

public class StayDetailsDto extends StayDetailsWithoutIdDto {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
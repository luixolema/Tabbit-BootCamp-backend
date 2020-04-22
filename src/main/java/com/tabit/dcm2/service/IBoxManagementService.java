package com.tabit.dcm2.service;

public interface IBoxManagementService {
    Boolean isBoxFree(String boxNumber);

    void reserveBox(String boxNumber);
}

package com.tabit.dcm2.controller;

import com.tabit.dcm2.service.IBoxManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/box")
public class BoxManagementController {
    @Autowired
    private IBoxManagementService boxManagementService;

    @PostMapping(path = "/isFree")
    public Boolean isBoxFree(@RequestBody String boxNumber) {
        return boxManagementService.isBoxFree(boxNumber);
    }
}

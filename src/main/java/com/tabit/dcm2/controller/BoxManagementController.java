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

    @GetMapping(path = "/{boxNumber}/isFree")
    public Boolean isBoxFree(@PathVariable String boxNumber) {
        return boxManagementService.isBoxFree(boxNumber);
    }
}

package com.tabit.dcm2.controller;

import com.tabit.dcm2.entity.User;
import com.tabit.dcm2.service.dto.ContextInfoDto;
import com.tabit.dcm2.service.impl.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ContextController {

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/contextInfo")
    public ContextInfoDto getContextInfo() {
        User loggedInUser = authenticationService.getLoggedInUser();

        return new ContextInfoDto.Builder()
                .withFirstName(loggedInUser.getName())
                .withDiveCenterName(loggedInUser.getDiveCenter().getName())
                .build();
    }
}

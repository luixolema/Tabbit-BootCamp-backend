package com.tabit.dcm2.controller;

import com.tabit.dcm2.service.dto.ContextInfoDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ContextController {

    @GetMapping("/contextInfo")
    public ContextInfoDto getContextInfo() {
        return new ContextInfoDto.Builder().withFirstName("userName").withDiveCenterName("Extra Divers Makadi Bay").build();
    }
}

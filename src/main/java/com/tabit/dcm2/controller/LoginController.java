package com.tabit.dcm2.controller;

import com.tabit.dcm2.security.JwtTokenResponse;
import com.tabit.dcm2.service.ILoginService;
import com.tabit.dcm2.service.dto.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    private ILoginService loginService;

    @PostMapping("/login")
    public JwtTokenResponse loginUser(@RequestBody LoginDto loginDto) {
        return loginService.generateJwtToken(loginDto.getLogin(), loginDto.getPassword());
    }
}

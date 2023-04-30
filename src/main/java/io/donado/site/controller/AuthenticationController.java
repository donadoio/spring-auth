package io.donado.site.controller;

import io.donado.site.dto.RegistrationDTO;
import io.donado.site.model.SecurityUser;
import io.donado.site.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/register")
    public SecurityUser registerUser(@RequestBody RegistrationDTO req) {
        return authenticationService.registerUser(req.getUsername(), req.getPassword());
    }
}

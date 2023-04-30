package io.donado.site.controller;

import io.donado.site.dto.RegistrationDTO;
import io.donado.site.model.SecurityUser;
import io.donado.site.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public SecurityUser registerUser(@RequestBody RegistrationDTO req) {
        System.out.println("Coming through");
        return authenticationService.registerUser(req.getUsername(), req.getPassword());
    }
}

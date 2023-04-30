package io.donado.site.controller;

import io.donado.site.dto.AuthenticateDTO;
import io.donado.site.dto.LoginResponseDTO;
import io.donado.site.model.SecurityUser;
import io.donado.site.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public SecurityUser registerUser(@RequestBody AuthenticateDTO req) {
        System.out.println("Coming through");
        return authenticationService.registerUser(req.getUsername(), req.getPassword());
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody AuthenticateDTO req) {
        return authenticationService.login(req.getUsername(), req.getPassword());
    }
}

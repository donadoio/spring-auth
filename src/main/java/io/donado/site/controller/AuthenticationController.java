package io.donado.site.controller;

import io.donado.site.dto.AuthenticateDTO;
import io.donado.site.dto.AuthenticateResponseDTO;
import io.donado.site.dto.RegisterDTO;
import io.donado.site.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public AuthenticateResponseDTO registerUser(@Valid @RequestBody RegisterDTO req) {
        System.out.println("Coming through");
        return authenticationService.registerUser(req.getEmail(), req.getUsername(), req.getPassword());
    }

    @PostMapping("/login")
    public AuthenticateResponseDTO login(@RequestBody AuthenticateDTO req) {
        return authenticationService.login(req.getUsername(), req.getPassword());
    }

    @GetMapping("/refresh")
    public AuthenticateResponseDTO refresh(@RequestHeader (HttpHeaders.AUTHORIZATION) String token) {
        return authenticationService.refresh(token.substring(7));
    }
}

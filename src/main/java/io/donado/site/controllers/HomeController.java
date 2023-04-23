package io.donado.site.controllers;

import io.donado.site.controllers.authentication.dto.LoginRequest;
import io.donado.site.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/")
    public ResponseEntity<String> home(@RequestBody LoginRequest loginRequest) {
        System.out.println("Inside");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        String token = jwtService.generateToken(authentication);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/any")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String any() {
        System.out.println("Hey!");
        return "nothing special";
    }

    @GetMapping("/user")
    public String user() {
        return "Hello user";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Hello admin";
    }
}

package io.donado.site.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/oauth2")
public class OAuthenticationController {

    @GetMapping("/code/user")
    public ResponseEntity<String> user(@AuthenticationPrincipal OAuth2User principal) {
        principal.getAttributes().forEach((k, v) -> {
            System.out.println((k + ":" + principal.getAttributes().get(k)));
        });
        return ResponseEntity.ok("Howdy");
        //return Collections.singletonMap("name", principal.getAttribute("name"));
    }
}

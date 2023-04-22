package io.donado.site.endpoints.info;

import io.donado.site.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/info")
@RequiredArgsConstructor
public class InfoController {
    private final JwtService jwtService;
    @GetMapping("waca")
    public ResponseEntity<String> hello(@RequestHeader Map<String, String> headers) {
        System.out.println(headers.get("authorization").substring(7));
        var user = jwtService.extractAllClaims(headers.get("authorization").substring(7));
        user.forEach((key, value) -> {
            System.out.println(String.format("%s = %s", key, value));
        });
        return ResponseEntity.ok("Hi friends");
    }
}
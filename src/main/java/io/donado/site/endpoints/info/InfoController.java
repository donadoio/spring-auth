package io.donado.site.endpoints.info;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/info")
public class InfoController {
    @GetMapping("waca")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hi friends");
    }
}

package io.donado.site.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {

    @GetMapping
    String hello() {
        return "Hello";
    }

    @GetMapping("/protected")
    String protect() {return "Hello";}

    @GetMapping("/userprotected")
    String userprotect() {return "Hello";}

    @GetMapping("/adminprotected")
    String adminprotect() {return "Hello";}
}

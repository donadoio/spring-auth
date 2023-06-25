package io.donado.site.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class MainController {

    @GetMapping
    String hello() {
        System.out.println("in");return "Hello";
    }

    @GetMapping("/private/protected")
    String protect() {return "Hello";}

    @GetMapping("/private/userprotected")
    String userprotect() {return "Hello";}

    @GetMapping("/private/adminprotected")
    String adminprotect() {return "Hello";}

    @GetMapping("/public/notprotected")
    String notprotect() {return "Hello";}
}

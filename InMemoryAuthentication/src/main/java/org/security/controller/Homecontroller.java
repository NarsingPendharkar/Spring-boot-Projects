package org.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Homecontroller {

    @GetMapping("/home")
    public String home(){
        return "WELCOME PAGE";
    }


    @GetMapping("/admin/page")
    public String homePage(){
        return "Hello public !";
    }


    @GetMapping("/public/page")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String publicPage(){
        return "Hello public !";
    }
}

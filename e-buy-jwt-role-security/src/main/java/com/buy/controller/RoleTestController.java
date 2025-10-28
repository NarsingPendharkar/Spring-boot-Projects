package com.buy.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RoleTestController {

    @GetMapping("/admin/test")
    public String adminOnly() {
        return "Welcome Admin!";
    }

    @GetMapping("/seller/test")
    public String sellerOnly() {
        return "Welcome Seller!";
    }

    @GetMapping("/customer/test")
    public String customerOnly() {
        return "Welcome Customer!";
    }
}

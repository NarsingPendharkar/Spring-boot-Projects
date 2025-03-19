package com.email.EmailSenderApp.controllers;
import org.springframework.http.HttpStatus;

import com.email.EmailSenderApp.Model.EmailDetails;
import com.email.EmailSenderApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.mail.javamail.JavaMailSender;

@Controller
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("home")
    @ResponseBody
    public String home() {
        return "home";
    }

    @PostMapping("sendEmail")
    @ResponseBody
    public String sendEmail(EmailDetails emailDetails) {

        emailService.sendEmail(emailDetails);
        return "Email sent successfully!";

    }

}

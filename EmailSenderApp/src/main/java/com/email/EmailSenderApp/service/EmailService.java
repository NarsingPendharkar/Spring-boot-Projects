package com.email.EmailSenderApp.service;

import com.email.EmailSenderApp.EmailSenderAppApplication;
import com.email.EmailSenderApp.Model.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(EmailDetails emailDetails){

        // Create a new mail object
        SimpleMailMessage mail = new SimpleMailMessage();
        // Set the recipient email address
        System.out.println(emailDetails.toString());
        mail.setTo(emailDetails.getRecipientEmail());
        mail.setSubject(emailDetails.getSubject());
        mail.setText(emailDetails.getBody());
        mailSender.send(mail);

        // Print success message to console when email is sent successfully
        System.out.println("Email sent successfully to " + emailDetails.getRecipientEmail());


    }

}

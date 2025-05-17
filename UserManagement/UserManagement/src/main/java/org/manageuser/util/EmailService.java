package org.manageuser.util;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.itextpdf.text.DocumentException;

import jakarta.activation.DataSource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;

@Component

public class EmailService {
	
	@Autowired JavaMailSender mailSender;
	
	
	// send welcome mails
	@Async
	public ResponseEntity<String> welcomeMail(String email,String userName) throws MessagingException{
		MimeMessage message=mailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message,true);
		helper.setTo(email);
		helper.setSubject("Welcome to Our  User Management System !");
		
		String htmlcontent="""
				<html>
              <body>
                <h3 style="color:blue">Hello, %s!</h3>
                <p>Welcome to our platform. Your registration was successful.</p>
                <p>We’re glad to have you on board.</p>
                <br/>
                <p>Regards,<br/>The Team</p>
              </body>
            </html> """.formatted(userName);
		helper.setText(htmlcontent,true);
		try {
			mailSender.send(message);
			return ResponseEntity.ok("Welcome mail sent !");
			
		} catch (Exception e) {
			return ResponseEntity.ok("Failed to send !");
		}
		
	}
	
	@Async
	public void sendWelcomeMailWithPdf(String toEmail, String name) throws MessagingException, IOException, DocumentException {
	    MimeMessage message = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message, true);

	    helper.setTo(toEmail);
	    helper.setSubject("Welcome to Our Platform");
	    helper.setText("Hi " + name + ",\n\nPlease find your welcome letter attached.\n\nRegards,\nTeam");

	    byte[] pdfBytes = new GeneratePDF().generateWelcomeOffer(name, toEmail);
	    DataSource dataSource = new ByteArrayDataSource(pdfBytes, "application/pdf");
	    helper.addAttachment("WelcomeLetter.pdf", dataSource);

	    mailSender.send(message);
	}

}

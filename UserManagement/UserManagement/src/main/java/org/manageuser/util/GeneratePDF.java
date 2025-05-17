package org.manageuser.util;

import java.io.ByteArrayOutputStream;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class GeneratePDF {
	
	@Async
	public byte[] generateWelcomeOffer(String name,String email) throws DocumentException {
		ByteArrayOutputStream output=new ByteArrayOutputStream();
		Document document=new Document();
		PdfWriter.getInstance(document, output);
		document.open();
		document.addTitle("Welcome Letter");
	    document.add(new Paragraph("Welcome to the System!"));
	    document.add(new Paragraph(" "));
	    document.add(new Paragraph("Dear " + name + ","));
	    document.add(new Paragraph("Thank you for registering with us."));
	    document.add(new Paragraph("Your email: " + email));
	    document.add(new Paragraph(" "));
	    document.add(new Paragraph("Regards,"));
	    document.add(new Paragraph("Team"));
	    document.close();
		return output.toByteArray();
	}

}

package in.ashokit.util;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {

	@Autowired
	private JavaMailSender mailSender;
	

    public void sendEmailWithAttachment(String toEmail, String subject, String body, ByteArrayInputStream pdfStream) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(body);

        // Attach the PDF file
        helper.addAttachment("generated_report.pdf", new ByteArrayResource(pdfStream.readAllBytes()));

        mailSender.send(message);
    }

}

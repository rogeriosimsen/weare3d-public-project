package br.com.weare3dproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.weare3dproject.enums.StatusEmail;
import br.com.weare3dproject.model.EmailModel;

@Service
public class EmailService {

	@Autowired
	JavaMailSender mailSender;
	
	public void sendEmail(EmailModel emailModel) {
		
		try {
			
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			
			String emailSubject = emailModel.getFullname() + " enviou uma mensagem!";
			String emailContent = "Nome do cliente: " + emailModel.getFullname() + "\n";
			emailContent += "Email do cliente: " + emailModel.getEmail() + "\n";
			emailContent += "Mensagem: " + emailModel.getMessage();
			
			mailMessage.setFrom(emailModel.getFrom());
			mailMessage.setTo(emailModel.getTo());
			mailMessage.setSubject(emailSubject);
			mailMessage.setText(emailContent);
			
			mailSender.send(mailMessage);
			
			emailModel.setStatusEmail(StatusEmail.SENT);
			
		} catch (MailException e) {
			
			emailModel.setStatusEmail(StatusEmail.ERROR);
			
			e.printStackTrace();
		}
	}
	
	
	
}

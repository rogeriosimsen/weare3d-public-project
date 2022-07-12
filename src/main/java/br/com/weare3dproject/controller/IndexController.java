package br.com.weare3dproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.weare3dproject.enums.StatusEmail;
import br.com.weare3dproject.model.EmailModel;
import br.com.weare3dproject.service.EmailService;

@RestController
@RequestMapping(value = "/mail-sender")
public class IndexController {
	
	@Autowired
	EmailService emailService;
	
	@PostMapping(value = "/send", produces = "application/json")
	public StatusEmail sendEmail(@RequestBody EmailModel emailModel){
		
		EmailModel email = new EmailModel();
		
		email.setFullname(emailModel.getFullname());
		email.setEmail(emailModel.getEmail());
		email.setMessage(emailModel.getMessage());
		
		emailService.sendEmail(email);
		
		return email.getStatusEmail();
	}

}

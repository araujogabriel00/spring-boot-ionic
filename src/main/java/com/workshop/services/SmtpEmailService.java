package com.workshop.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.workshop.entitites.Pedido;

public class SmtpEmailService extends AbstractSendEmail{

	
	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);
	
	@Autowired
	private MailSender mailSender;
	
	
	private JavaMailSender javaMailSender;
	
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		
		LOG.info("Enviando de email.....");
		mailSender.send(msg);
		LOG.info("Email enviado");
		
		
	}


	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		
		LOG.info("Enviando de email.....");
		javaMailSender.send(msg);
		LOG.info("Email enviado");
	}


	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido obj) {
		// TODO Auto-generated method stub
		
	}
	
	

}

package com.workshop.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;


public class MockMailService extends AbstractSendEmail {

	private static final Logger LOG = LoggerFactory.getLogger(MockMailService.class);

	@Override
	public void sendEmail(SimpleMailMessage msg) {

		LOG.info("Simulando envio de email.....");
		LOG.info(msg.toString());
		LOG.info("Email enviado");

	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {

		LOG.info("Simulando envio de email HTML.....");
		LOG.info(msg.toString());
		LOG.info("Email enviado");
	}

}

package com.workshop.services;

import org.springframework.mail.SimpleMailMessage;

import com.workshop.entitites.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido pedido);

	void sendEmail(SimpleMailMessage msg);

}

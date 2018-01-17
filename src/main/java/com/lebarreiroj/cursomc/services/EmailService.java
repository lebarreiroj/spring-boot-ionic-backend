package com.lebarreiroj.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.lebarreiroj.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
}

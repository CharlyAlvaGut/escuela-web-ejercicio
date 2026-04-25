package com.ipn.escuela_web.features.email.service;


public interface IEmailService {
	void enviarCorreo(String to, String subject, String text);
}
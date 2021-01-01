package com.workshop.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.workshop.services.DBservice;
import com.workshop.services.EmailService;
import com.workshop.services.MockMailService;

@Configuration
@Profile("test") // SERÃO EXECUTADAS APENAS AS OPERAÇÕES QUE ESTIVEREM NO PERFIL DE TESTE
public class TestConfig {

	@Autowired
	private DBservice dBservice;
	
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	@Bean
	public boolean instantiateDatabase() throws ParseException {
		
		if(!"create".equals(strategy)) {
			return false;
		}

		dBservice.instantiateTestDatabase();

		return true;

	}

	@Bean
	public EmailService emailService() {
		return new MockMailService();
	}
	
	
	
}

package com.workshop.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.workshop.services.DBservice;
import com.workshop.services.EmailService;
import com.workshop.services.SmtpEmailService;

@Configuration //ANOTAÇÃO DE CLASSE DE CONFIGURÇÃO
@Profile("dev") // NOME DO PERFIL QUE SERÁ USADO NO APPLICATION.PROPERTIES QUANDO O PROJETO ESTIVER EM FASE DE DEV
public class DevConfig {

	@Autowired
	private DBservice dBservice; //DB QUE SERÁ INICIADO APÓS OS TESTES E QUE CONTÉM AS INSTACIAÇÕES
	
	
	@Value("${spring.jpa.hibernate.ddl-auto}") //CHAVE DO APPLICATION.PROPERTIES QUE CORRESPONDE A DDL 
	private String strategy;

	@Bean
	public boolean instantiateDatabase() throws ParseException { ///IRÁ TESTAR SE O BANCO A CHAVE PASSADA ESTÁ COM O VALOR "CREATE" COMO PASSADO NO APPLICATION.TEST E DEPOIS INICIARA O DB DE TESTE
		
		if(!"create".equals(strategy)) {
			return false;
		}

		dBservice.instantiateTestDatabase();

		return true;

	}

	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
	
	
	
}

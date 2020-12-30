package com.workshop.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.workshop.services.DBservice;

@Configuration
@Profile("test") // SERÃO EXECUTADAS APENAS AS OPERAÇÕES QUE ESTIVEREM NO PERFIL DE TESTE
public class TestConfig {

	@Autowired
	private DBservice dBservice;

	@Bean
	public boolean instantiateDatabase() throws ParseException {

		dBservice.instantiateTestDatabase();

		return true;

	}

}

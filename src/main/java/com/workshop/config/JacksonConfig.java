package com.workshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workshop.entitites.PagamentoBoleto;
import com.workshop.entitites.PagamentoCartão;

@Configuration//CLASSES QUE FOREM DE CONFIGURAÇÃO DEVERAM TER ESTA ANOTAÇÃO
///REGISTRA AS SUBCLASSES para permitir a instanciação de subclasses a partir de dados JSON
public class JacksonConfig {
	// https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-ofinterfaceclass-without-hinting-the-pare
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(PagamentoCartão.class);
				objectMapper.registerSubtypes(PagamentoBoleto.class);
				super.configure(objectMapper);
			};
		};
		return builder;
	}
}

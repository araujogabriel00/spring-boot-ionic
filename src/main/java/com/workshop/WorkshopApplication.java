package com.workshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

///CLASSE DE APLICAÇÃO/PRINCIPAL
///IMPLEMENTAR O COMMANDLINERUNNER


@SpringBootApplication
public class WorkshopApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(WorkshopApplication.class, args);
	}

	/// METODO GERADO APÓS A IMPLEMENTAÇÃO DO COMMANDLINNERUNNER
	@Override
	public void run(String... args) throws Exception {

	}

}

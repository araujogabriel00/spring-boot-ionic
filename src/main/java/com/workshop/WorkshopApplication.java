package com.workshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

///CLASSE DE APLICAÇÃO
///ESTANCIAR AS CLASSES REPOSITORIOS QUE IRÃO UTILIZAR OS DADOS
///IMPLEMENTAR O COMMANDLINERUNNER
///A CLASSE DE REPOSITORIO DEVERÁ SALVAR OS OBJS INSTANCIADOS
///OS OBJ DEVERAM SER INSTACIADOS DENTRO DA FUNÇÃO QUE O COMMANDLINERUNNER GERA

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

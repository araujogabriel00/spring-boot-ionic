package com.workshop;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.workshop.entitites.Categoria;
import com.workshop.repositories.CategoriaRepo;

///CLASSE DE APLICAÇÃO
///ESTANCIAR AS CLASSES REPOSITORIOS QUE IRÃO UTILIZAR OS DADOS
///IMPLEMENTAR O COMMANDLINERUNNER
///A CLASSE DE REPOSITORIO DEVERÁ SALVAR OS OBJS INSTANCIADOS
@SpringBootApplication
public class WorkshopApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepo categoriarepo;
	
	
	public static void main(String[] args) {
		SpringApplication.run(WorkshopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		categoriarepo.saveAll(Arrays.asList(cat1,cat2));
		
		
		
		
		
	

		
		
	}

	
	
	
}

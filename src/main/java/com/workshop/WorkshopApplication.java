package com.workshop;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.workshop.entitites.Categoria;
import com.workshop.entitites.Produto;
import com.workshop.repositories.CategoriaRepo;
import com.workshop.repositories.ProdutoRepo;

///CLASSE DE APLICAÇÃO
///ESTANCIAR AS CLASSES REPOSITORIOS QUE IRÃO UTILIZAR OS DADOS
///IMPLEMENTAR O COMMANDLINERUNNER
///A CLASSE DE REPOSITORIO DEVERÁ SALVAR OS OBJS INSTANCIADOS
///OS OBJ DEVERAM SER INSTACIADOS DENTRO DA FUNÇÃO QUE O COMMANDLINERUNNER GERA

@SpringBootApplication
public class WorkshopApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepo categoriarepo;

	@Autowired
	private ProdutoRepo produtorepo;

	public static void main(String[] args) {
		SpringApplication.run(WorkshopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		categoriarepo.saveAll(Arrays.asList(cat1, cat2));
		produtorepo.saveAll(Arrays.asList(p1, p2, p3));

	}

}

package com.workshop;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.workshop.entitites.Categoria;
import com.workshop.entitites.Cidade;
import com.workshop.entitites.Cliente;
import com.workshop.entitites.Endereco;
import com.workshop.entitites.Estado;
import com.workshop.entitites.Produto;
import com.workshop.enums.TipoCliente;
import com.workshop.repositories.CategoriaRepo;
import com.workshop.repositories.CidadeRepo;
import com.workshop.repositories.ClienteRepo;
import com.workshop.repositories.EnderecoRepo;
import com.workshop.repositories.EstadoRepo;
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

	@Autowired
	private EstadoRepo estadorepo;

	@Autowired
	private CidadeRepo cidaderepo;

	@Autowired
	private ClienteRepo clienterepo;
	
	@Autowired
	private EnderecoRepo enderecorepo;

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

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade cd1 = new Cidade(null, "Uberlandia", est1);
		Cidade cd2 = new Cidade(null, "São Paulo", est2);
		Cidade cd3 = new Cidade(null, "Campinas", est2);

		Cliente cli = new Cliente(null, "Gabriel de Araujo", "gabriel.araujos@sempreceub.com", "051.251.921.80",
				TipoCliente.PESSOAFISICA);
		cli.getTelefones().addAll(Arrays.asList("993783203", "992944643"));

		Endereco e1 = new Endereco(null, "Rua dos Burracos", "38", "MR 11", "Setor Sul", "73753010", cli, cd2);

		cli.getEnderecos().addAll(Arrays.asList(e1));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriarepo.saveAll(Arrays.asList(cat1, cat2));
		produtorepo.saveAll(Arrays.asList(p1, p2, p3));

		est1.getCidades().addAll(Arrays.asList(cd1));
		est2.getCidades().addAll(Arrays.asList(cd2, cd3));

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		estadorepo.saveAll(Arrays.asList(est1, est2));
		cidaderepo.saveAll(Arrays.asList(cd1, cd2, cd3));
		clienterepo.saveAll(Arrays.asList(cli));
		enderecorepo.saveAll(Arrays.asList(e1));

	}

}

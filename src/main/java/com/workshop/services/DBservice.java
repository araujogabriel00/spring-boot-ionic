package com.workshop.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshop.entitites.Categoria;
import com.workshop.entitites.Cidade;
import com.workshop.entitites.Cliente;
import com.workshop.entitites.Endereco;
import com.workshop.entitites.Estado;
import com.workshop.entitites.ItemPedido;
import com.workshop.entitites.Pagamento;
import com.workshop.entitites.PagamentoCartão;
import com.workshop.entitites.Pedido;
import com.workshop.entitites.Produto;
import com.workshop.enums.EstadoPagamento;
import com.workshop.enums.TipoCliente;
import com.workshop.repositories.CategoriaRepo;
import com.workshop.repositories.CidadeRepo;
import com.workshop.repositories.ClienteRepo;
import com.workshop.repositories.EnderecoRepo;
import com.workshop.repositories.EstadoRepo;
import com.workshop.repositories.ItemPedidoRepo;
import com.workshop.repositories.PagamentoRepo;
import com.workshop.repositories.PedidoRepo;
import com.workshop.repositories.ProdutoRepo;

@Service
public class DBservice {

	/// TODAS AS CLASSE QUE NECESSITAM ALGUM TIPO DE SALVAMENTO PRECISAM DE UM
	/// REPOSITORIO
	/// AS CLASSE REPOSITORIO DEVEM CONTER A ANOTAÇÃO @Autowired
	/// CLASSE EXCLUSIVA PARA INSTACIAMENTO DOS OBJETOS NO BANCO DE DADOS

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

	@Autowired
	private PedidoRepo pedidorepo;

	@Autowired
	private PagamentoRepo pagamentorepo;

	@Autowired
	private ItemPedidoRepo itemPedidoRepo;

	public void instantiateTestDatabase() throws ParseException {

		// INSTANCIAÇÃO OBJS
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		Categoria cat3 = new Categoria(null, "Video Games");
		Categoria cat4 = new Categoria(null, "Eletrodomesticos");
		Categoria cat5 = new Categoria(null, "Computadores");
		
	

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

		SimpleDateFormat sdf = new SimpleDateFormat(("dd/MM/yyyy HH:mm"));

		Pedido pd1 = new Pedido(null, sdf.parse("12/12/2020 20:11"), cli, e1);

		Pagamento pgto1 = new PagamentoCartão(null, EstadoPagamento.QUITADO, pd1, 6);

		ItemPedido itemPedido = new ItemPedido(pd1, p1, 0.00, 1, 2000.00);

		// GET E SET
		pd1.setPagamento(pgto1);
		cli.getEnderecos().addAll(Arrays.asList(e1));
		cli.getPedidos().addAll(Arrays.asList(pd1));
		pd1.getItens().addAll(Arrays.asList(itemPedido));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		p1.getItens().addAll(Arrays.asList(itemPedido));

		est1.getCidades().addAll(Arrays.asList(cd1));
		est2.getCidades().addAll(Arrays.asList(cd2, cd3));

		/// SALVAR REPOSITORIOS

		categoriarepo.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5));

		produtorepo.saveAll(Arrays.asList(p1, p2, p3));

		estadorepo.saveAll(Arrays.asList(est1, est2));

		cidaderepo.saveAll(Arrays.asList(cd1, cd2, cd3));

		clienterepo.saveAll(Arrays.asList(cli));

		enderecorepo.saveAll(Arrays.asList(e1));

		pedidorepo.saveAll(Arrays.asList(pd1));

		pagamentorepo.saveAll(Arrays.asList(pgto1));

		itemPedidoRepo.saveAll(Arrays.asList(itemPedido));

	}

}

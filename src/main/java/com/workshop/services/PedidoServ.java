package com.workshop.services;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshop.entitites.ItemPedido;
import com.workshop.entitites.PagamentoBoleto;
import com.workshop.entitites.Pedido;
import com.workshop.enums.EstadoPagamento;
import com.workshop.repositories.ItemPedidoRepo;
import com.workshop.repositories.PagamentoRepo;
import com.workshop.repositories.PedidoRepo;

///RESPONSAVEL POR PASSAR AS CATEGORIAS AOS CONTROLADORES REST
///INSTACIAR REPOSITORIO DA CLASSE
///NÃO ESQUECER DAS ANOTAÇÕES SERVICE E AUTOWIRED

@Service
public class PedidoServ {

	@Autowired
	private PedidoRepo pedidoRepo;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private PagamentoRepo pagamentoRepo;

	@Autowired
	private ItemPedidoRepo itemPedidoRepo;

	@Autowired
	private ClienteServ clienteServ;

	@Autowired
	private ProdutoServ produtoServ;
	
	@Autowired
	private EmailService emailService;

	public Pedido find(Integer id) {
		Optional<Pedido> obj = pedidoRepo.findById(id);
		return obj.orElse(null);

	}

	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteServ.find(obj.getCliente().getId()));
		obj.getPagamento().setEstadopagamento(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);

		if (obj.getPagamento() instanceof PagamentoBoleto) {
			PagamentoBoleto pgto = (PagamentoBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pgto, obj.getInstante());
		}

		obj = pedidoRepo.save(obj);
		pagamentoRepo.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoServ.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);

		}

		itemPedidoRepo.saveAll(obj.getItens());
		emailService.sendOrderConfirmationEmail(obj);
		return obj;

	}
}
// USAR TRANSACTIONAL NO INSERT
// USAR PEDIDO SERVICE NÃO REPOSITORY

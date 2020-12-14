package com.workshop.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshop.entitites.Pedido;
import com.workshop.repositories.PedidoRepo;

///RESPONSAVEL POR PASSAR AS CATEGORIAS AOS CONTROLADORES REST
///INSTACIAR REPOSITORIO DA CLASSE
///NÃO ESQUECER DAS ANOTAÇÕES SERVICE E AUTOWIRED


@Service
public class PedidoServ {

	@Autowired
	private PedidoRepo pedidoRepo ; 

	public Pedido find(Integer id) {
		Optional<Pedido> obj = pedidoRepo.findById(id);
		return obj.orElse(null);

	}

}
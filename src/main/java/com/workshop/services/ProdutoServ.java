package com.workshop.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshop.entitites.Produto;
import com.workshop.repositories.ProdutoRepo;

///RESPONSAVEL POR PASSAR AS CATEGORIAS AOS CONTROLADORES REST
///INSTACIAR REPOSITORIO DA CLASSE
///NÃO ESQUECER DAS ANOTAÇÕES SERVICE E AUTOWIRED


@Service
public class ProdutoServ {

	@Autowired
	private ProdutoRepo produtorepo; 

	public Produto find(Integer id) {
		Optional<Produto> obj = produtorepo.findById(id);
		return obj.orElse(null);

	}

}
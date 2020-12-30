package com.workshop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.workshop.entitites.Categoria;
import com.workshop.entitites.Produto;
import com.workshop.repositories.CategoriaRepo;
import com.workshop.repositories.ProdutoRepo;
///RESPONSAVEL POR PASSAR AS CATEGORIAS AOS CONTROLADORES REST
///INSTACIAR REPOSITORIO DA CLASSE
///NÃO ESQUECER DAS ANOTAÇÕES SERVICE E AUTOWIRED

@Service
public class ProdutoServ {

	@Autowired
	private ProdutoRepo produtorepo;

	@Autowired
	private CategoriaRepo categoriaRepo;

	public Produto find(Integer id) {
		Optional<Produto> obj = produtorepo.findById(id);
		return obj.orElse(null);

	}

	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepo.findAllById(ids);
		return produtorepo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}

	
}
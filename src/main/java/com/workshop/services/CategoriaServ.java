package com.workshop.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshop.entitites.Categoria;
import com.workshop.repositories.CategoriaRepo;

///RESPONSAVEL POR PASSAR AS CATEGORIAS AOS CONTROLADORES REST
///INSTACIAR REPOSITORIO DA CLASSE
///NÃO ESQUECER DAS ANOTAÇÕES SERVICE E AUTOWIRED


@Service
public class CategoriaServ {

	@Autowired
	private CategoriaRepo categoriarepo;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = categoriarepo.findById(id);
		return obj.orElse(null);

	}

}
package com.workshop.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.workshop.entitites.Categoria;
import com.workshop.services.CategoriaServ;

///CATEGORIA PRINCIPAL
///DEVE CONTER AS ANOTAÇÕES RESTCONTROLLER E REQUESTMAPPING
///A FUNÇÃO RESPONSAVEL PELABUSCA DE CONTER A ANOTAÇÃO REQUESTMAPPING
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaServ categoriaserv;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {

		Categoria obj = categoriaserv.find(id);
		return ResponseEntity.ok().body(obj);

	}

}


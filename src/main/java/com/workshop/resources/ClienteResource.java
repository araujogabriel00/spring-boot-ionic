package com.workshop.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.workshop.entitites.Cliente;
import com.workshop.services.ClienteServ;

///CATEGORIA PRINCIPAL
///DEVE CONTER AS ANOTAÇÕES RESTCONTROLLER E REQUESTMAPPING
///A FUNÇÃO RESPONSAVEL PELABUSCA DE CONTER A ANOTAÇÃO REQUESTMAPPING
@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteServ clienteserv;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {

		Cliente obj = clienteserv.find(id);
		return ResponseEntity.ok().body(obj);

	}

}

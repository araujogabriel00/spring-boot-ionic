package com.workshop.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.workshop.dto.ClienteDTO;
import com.workshop.dto.ClienteNewDTO;
import com.workshop.entitites.Cliente;
import com.workshop.services.ClienteServ;

///CATEGORIA PRINCIPAL
///DEVE CONTER AS ANOTAÇÕES RESTCONTROLLER E REQUESTMAPPING
///A FUNÇÃO RESPONSAVEL PELA BUSCA DE CONTER A ANOTAÇÃO REQUESTMAPPING
@RestController
@RequestMapping(value = "/clientes") // O VALUE DEFINE COMO SERÁ CHAMADO O RESOURCE NA URL
public class ClienteResource {

	/// RESOURSCES DEVEM TER A CLASSE DE SERVIÇO DE SUA CORRESPONDENTE ENTIDADE
	@Autowired
	private ClienteServ clienteserv;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {

		Cliente obj = clienteserv.find(id);
		return ResponseEntity.ok().body(obj);

	}

	/// FAZER A NOVA CATEGORIA VIR COM UM NOVO ID
	/// FAZER A NOVA CATEGORIA VIR COM UM NOVO ID
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> insert(@Valid @RequestBody ClienteNewDTO clienteNewDTO) {
		Cliente obj = clienteserv.fromDTO(clienteNewDTO);
		obj = clienteserv.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).build();

	}

	// ATUALIZAR CATEGORIA
	// UTILIZANDO BEAN VALIDATION
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Integer id) {

		Cliente cliente = clienteserv.fromDTO(clienteDTO);
		cliente.setId(id);
		cliente = clienteserv.update(cliente);

		return ResponseEntity.noContent().build();
	}

	// DELETAR CATEGORIA
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		clienteserv.delete(id);
		return ResponseEntity.noContent().build();

	}

	/// MOSTRAR TODAS AS CATEGORIAS
	/// BUSCA E CONVERTE A LISTA EM DTO´S
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> lista = clienteserv.findAll();

		// CONVERSÃO DE LISTA EM OUTRA LISTA
		List<ClienteDTO> listaDTO = lista.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDTO);

	}

	/// PAGINAÇÃO COM PARAMETROS OPCIONAIS NA REQUISIÇÃO
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Cliente> lista = clienteserv.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listaDTO = lista.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listaDTO);
	}

}

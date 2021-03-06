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

import com.workshop.dto.CategoriaDTO;
import com.workshop.entitites.Categoria;
import com.workshop.services.CategoriaServ;

///CATEGORIA PRINCIPAL
///DEVE CONTER AS ANOTAÇÕES RESTCONTROLLER E REQUESTMAPPING
///A FUNÇÃO RESPONSAVEL PELA BUSCA DE CONTER A ANOTAÇÃO REQUESTMAPPING
@RestController
@RequestMapping(value = "/categorias") // O VALUE DEFINE COMO SERÁ CHAMADO O RESOURCE NA URL
public class CategoriaResource {

	/// RESOURSCES DEVEM TER A CLASSE DE SERVIÇO DE SUA CORRESPONDENTE ENTIDADE
	@Autowired
	private CategoriaServ categoriaserv;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {

		Categoria obj = categoriaserv.find(id);
		return ResponseEntity.ok().body(obj);

	}

	/// FAZER A NOVA CATEGORIA VIR COM UM NOVO ID
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> insert(@Valid @RequestBody CategoriaDTO categoriaDTO) {
		Categoria obj = categoriaserv.fromDTO(categoriaDTO);
		obj = categoriaserv.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).build();

	}

	// ATUALIZAR CATEGORIA
	// UTILIZANDO BEAN VALIDATION
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO categoriaDTO, @PathVariable Integer id) {

		Categoria categoria = categoriaserv.fromDTO(categoriaDTO);
		categoria.setId(id);
		categoria = categoriaserv.update(categoria);

		return ResponseEntity.noContent().build();
	}

	// DELETAR CATEGORIA
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		categoriaserv.delete(id);
		return ResponseEntity.noContent().build();

	}

	/// MOSTRAR TODAS AS CATEGORIAS
	/// BUSCA E CONVERTE A LISTA EM DTO´S
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<Categoria> lista = categoriaserv.findAll();

		// CONVERSÃO DE LISTA EM OUTRA LISTA
		List<CategoriaDTO> listaDTO = lista.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDTO);

	}

	/// PAGINAÇÃO COM PARAMETROS OPCIONAIS NA REQUISIÇÃO
	/// O sistema informa os nomes de todas categorias ordenadamente
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Categoria> lista = categoriaserv.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> listaDTO = lista.map(obj -> new CategoriaDTO(obj));
		return ResponseEntity.ok().body(listaDTO);
	}

}

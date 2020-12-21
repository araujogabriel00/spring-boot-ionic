package com.workshop.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.workshop.dto.CategoriaDTO;
import com.workshop.entitites.Categoria;
import com.workshop.repositories.CategoriaRepo;
import com.workshop.resources.DataIntegrityException;

///RESPONSAVEL POR PASSAR AS CATEGORIAS AOS CONTROLADORES REST
///INSTACIAR REPOSITORIO DA CLASSE
///NÃO ESQUECER DAS ANOTAÇÕES SERVICE E AUTOWIRED

@Service
public class CategoriaServ {

	@Autowired
	private CategoriaRepo categoriarepo;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = categoriarepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName(), "Deu ruim"));
	}

	// INSERIR UMA CATEGORIA
	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return categoriarepo.save(categoria);
	}

	// ATUALIZAR UMA CATEGORIA
	public Categoria update(Categoria categoria) {
		find(categoria.getId());
		return categoriarepo.save(categoria);
	}

	// DELETAR UMA CATEGORIA
	public void delete(Integer id) {
		find(id);
		try {
			categoriarepo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos ");

		}

	}

	/// ACHAR TODAS AS CATEGORIAS
	public List<Categoria> findAll() {
		return categoriarepo.findAll();
	}

	/// USAR PARA BUSCA CONTROLADA PARA ECONOMIA DE MEMÓRIA
	/// PAGINAÇÃO
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		return categoriarepo.findAll(pageRequest);

	}

	///INSTACIA UMA CATEGORIA APARTIR DE UM DTO
	public Categoria fromDTO(CategoriaDTO objDTO) {

		return new Categoria(objDTO.getId(), objDTO.getNome());

	}

}
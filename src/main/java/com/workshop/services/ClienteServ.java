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

import com.workshop.dto.ClienteDTO;
import com.workshop.entitites.Cliente;
import com.workshop.repositories.ClienteRepo;
import com.workshop.resources.DataIntegrityException;

///RESPONSAVEL POR PASSAR AS CATEGORIAS AOS CONTROLADORES REST
///INSTACIAR REPOSITORIO DA CLASSE
///NÃO ESQUECER DAS ANOTAÇÕES SERVICE E AUTOWIRED

@Service
public class ClienteServ {

	@Autowired
	private ClienteRepo clienterepo;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = clienterepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName(), "Deu ruim"));
	}

	// INSERIR UMA CATEGORIA
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		return clienterepo.save(cliente);
	}

	// ATUALIZAR UMA CATEGORIA
	public Cliente update(Cliente cliente) {
		Cliente newCliente = find(cliente.getId());
		updateData(newCliente, cliente);
		return clienterepo.save(newCliente);
	}

	private void updateData(Cliente newCliente, Cliente cliente) {
		newCliente.setNome(cliente.getNome());
		newCliente.setEmail(cliente.getEmail());

	}

	// DELETAR UMA CATEGORIA
	public void delete(Integer id) {
		find(id);
		try {
			clienterepo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma cliente que possui produtos ");

		}

	}

	/// ACHAR TODAS AS CATEGORIAS
	public List<Cliente> findAll() {
		return clienterepo.findAll();
	}

	/// USAR PARA BUSCA CONTROLADA PARA ECONOMIA DE MEMÓRIA
	/// PAGINAÇÃO
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		return clienterepo.findAll(pageRequest);

	}

	/// INSTANCIAÇÃO DO CLIENTE APARTIR DO DTO
	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);

	}

}
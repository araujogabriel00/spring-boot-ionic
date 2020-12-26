package com.workshop.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.workshop.dto.ClienteDTO;
import com.workshop.dto.ClienteNewDTO;
import com.workshop.entitites.Cidade;
import com.workshop.entitites.Cliente;
import com.workshop.entitites.Endereco;
import com.workshop.enums.TipoCliente;
import com.workshop.repositories.ClienteRepo;
import com.workshop.repositories.EnderecoRepo;
import com.workshop.resources.DataIntegrityException;

///RESPONSAVEL POR PASSAR AS CATEGORIAS AOS CONTROLADORES REST
///INSTACIAR REPOSITORIO DA CLASSE
///NÃO ESQUECER DAS ANOTAÇÕES SERVICE E AUTOWIRED

@Service
public class ClienteServ {

	@Autowired
	private ClienteRepo clienterepo;

	@Autowired
	private EnderecoRepo enderecoRepository;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = clienterepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName(), "Deu ruim"));
	}
	// INSERIR UMA CATEGORIA

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = clienterepo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
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

	public Cliente fromDTO(ClienteNewDTO clienteNewDTO) {
		Cliente cli = new Cliente(null, clienteNewDTO.getNome(), clienteNewDTO.getEmail(), clienteNewDTO.getCpfOUcnpj(),
				TipoCliente.toEnum(clienteNewDTO.getTipo()));
		Cidade cid = new Cidade(clienteNewDTO.getCidadeID(), null, null);

		Endereco end = new Endereco(null, clienteNewDTO.getLogradouro(), clienteNewDTO.getNumero(),
				clienteNewDTO.getComplemento(), clienteNewDTO.getBairro(), clienteNewDTO.getCep(), cli, cid);

		cli.getEnderecos().add(end);
		cli.getTelefones().add(clienteNewDTO.getTelefone());
		if (clienteNewDTO.getTelefone2() != null) {
			cli.getTelefones().add(clienteNewDTO.getTelefone2());
		}
		if (clienteNewDTO.getTelefone3() != null) {
			cli.getTelefones().add(clienteNewDTO.getTelefone3());
		}
		return cli;
	}
}
package com.workshop.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.workshop.dto.ClienteDTO;
import com.workshop.dto.ClienteNewDTO;
import com.workshop.entitites.Cidade;
import com.workshop.entitites.Cliente;
import com.workshop.entitites.Endereco;
import com.workshop.enums.Perfil;
import com.workshop.enums.TipoCliente;
import com.workshop.exceptions.AuthorizationException;
import com.workshop.exceptions.DataIntegrityException;
import com.workshop.repositories.ClienteRepo;
import com.workshop.repositories.EnderecoRepo;
import com.workshop.security.UserSS;

///RESPONSAVEL POR PASSAR AS CATEGORIAS AOS CONTROLADORES REST
///INSTACIAR REPOSITORIO DA CLASSE
///NÃO ESQUECER DAS ANOTAÇÕES SERVICE E AUTOWIRED

@Service
public class ClienteServ {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private ClienteRepo clienterepo;

	@Autowired
	private EnderecoRepo enderecoRepository;

	@Autowired
	private S3Service s3Service;

	@Autowired
	private ImageService imageService;

	@Value("${img.prefix.client.profile}")
	private String prefix;

	@Value("${img.profile.size}")
	private Integer size;

	public Cliente find(Integer id) {
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getID())) {
			throw new AuthorizationException("Acesso negado");

		}
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
			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");

		}

	}

	/// ACHAR TODAS AS CATEGORIAS
	public List<Cliente> findAll() {
		return clienterepo.findAll();
	}

	public Cliente findByEmail(String email) {
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}

		Cliente obj = clienterepo.findByEmail(email);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + user.getID() + ", Tipo: " + Cliente.class.getName(), email);
		}
		return obj;
	}

	/// USAR PARA BUSCA CONTROLADA PARA ECONOMIA DE MEMÓRIA
	/// PAGINAÇÃO
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		return clienterepo.findAll(pageRequest);

	}

	/// INSTANCIAÇÃO DO CLIENTE APARTIR DO DTO
	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null, null);

	}

	public Cliente fromDTO(ClienteNewDTO clienteNewDTO) {
		Cliente cli = new Cliente(null, clienteNewDTO.getNome(), clienteNewDTO.getEmail(), clienteNewDTO.getCpfOUcnpj(),
				TipoCliente.toEnum(clienteNewDTO.getTipo()), bCryptPasswordEncoder.encode(clienteNewDTO.getSenha()));
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

	public URI uploadProfilePicture(MultipartFile multipartFile) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso Negado");
		}

		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);

		String fileName = prefix + user.getID() + ".jpg";

		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");

	}

}
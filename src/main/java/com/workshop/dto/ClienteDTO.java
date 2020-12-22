package com.workshop.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.workshop.entitites.Cliente;

//DEFINIR OS DADOS QUE TRAFEGARAM QUANDO FOREM FEITAS OPERAÇÕES BÁSICAS DA CLASSE EM QUESTÃO
public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	//Validação sintática com Bean Validation - BACKEND
	@NotEmpty(message = "Preechimento Obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;

	private Integer id;
	
	@NotEmpty(message = "Preechimento Obrigatório")
	@Email(message = "Email inválido")
	private String email;

	public ClienteDTO() {

	}

	public ClienteDTO(Cliente obj) {
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}

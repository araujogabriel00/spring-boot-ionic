package com.workshop.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.workshop.entitites.Categoria;

//DEFINIR OS DADOS QUE TRAFEGARAM QUANDO FOREM FEITAS OPERAÇÕES BÁSICAS DA CLASSE EM QUESTÃO
public class CategoriaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	//Validação sintática com Bean Validation - BACKEND
	@NotEmpty(message = "Preechimento Obrigatório")
	@Length(min = 8, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;

	private Integer id;

	public CategoriaDTO() {

	}

	// RESPONSAVEL POR INSTACIAR O DTO A PARTIR DE UM OBJ CATEGORIA
	public CategoriaDTO(Categoria categoria) {
		id = categoria.getId();
		nome = categoria.getNome();
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

}

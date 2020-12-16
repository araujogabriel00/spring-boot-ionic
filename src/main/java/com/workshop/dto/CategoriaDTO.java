package com.workshop.dto;

import java.io.Serializable;

import com.workshop.entitites.Categoria;

//DEFINIR OS DADOS QUE TRAFEGARAM QUANDO FOREM FEITAS OPERAÇÕES BÁSICAS DA CLASSE EM QUESTÃO
public class CategoriaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private Integer id;

	public CategoriaDTO() {

	}

	//RESPONSAVEL POR INSTACIAR O DTO A PARTIR DE UM OBJ CATEGORIA
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

package com.workshop.entitites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

//COLEÇÕES NÃO FAZEM PARTE DOS CONSTRUTORES SOMENTE DEVEM TER GET E SET

@Entity // ENTIDADE DE DOMINIO -> DEMOSTRA QUE ESSA ENTIDADE DEVE APARECER NO BD
@Table(name = "categoria") // NOME DA TABELA NO BD
public class Categoria implements Serializable { // TODAS AS ENTIDADES QUE NÃO SEJAM FRACAS DEVEM IMPLEMENTAR O
													// SERIALIZABLE

	private static final long serialVersionUID = 1L;

	@Id /// ID DA CLASSE
	@GeneratedValue(strategy = GenerationType.IDENTITY) /// O QUE ESSA VAIRIAVEL IRÁ FAZER
	private Integer id;

	@Column(name = "nome") // NOME DA COLUNA NO BANCO DE DADOS
	private String nome;

	// COLEÇÃO PRODUTOS
	@JsonIgnore /// USAR PARA NÃO TER REFERÊNCIA CICLICAS
	@ManyToMany(mappedBy = "categorias") /// COMO SERÁ O RELACIONAMENTO DAS ENTIDADES ESTANCIADAS E QUEM ESTÁ MAPEANDO
											/// DO OUTRO LADO
	private List<Produto> produtos = new ArrayList<>();/// COLEÇÃO NÃO DEVEM SER INCLUIDAS NOS CONSTRUTORES SOMENTEA
														/// GETS E SETS

	//CONSTRUTOR VAZIO
	public Categoria() {

	}

	//CONSTRUTOR PADRAO
	public Categoria(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	////GETS E SETS

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;

	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	// HASH CODE E EQUALS

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
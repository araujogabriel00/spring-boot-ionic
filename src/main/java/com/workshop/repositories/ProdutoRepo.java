package com.workshop.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.workshop.entitites.Categoria;
import com.workshop.entitites.Produto;

///RESPONSAVEL POR PASSAR IR NO BD E BUSCAR OS DADOS
///REPOSITORIOS DEVEM SER INTERFACES
///DEVE ESTENDER O JPA REPOSITORY
///CLASSE RESPONSAVEL PELA OPERAÇÕES DE PESQUISA EX.: FINDBYID
@Repository
public interface ProdutoRepo extends JpaRepository<Produto, Integer> {

	///COMO DEVERÁ SER FEITA A BUSCA
	//NO SITE DE DOCS DO SPRING JÁ TEM OS NOMES DAS CONSULTAS
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias") 
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);

}


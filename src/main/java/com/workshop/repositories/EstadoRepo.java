package com.workshop.repositories;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.workshop.entitites.Estado;

///RESPONSAVEL POR PASSAR IR NO BD E BUSCAR OS DADOS
///REPOSITORIOS DEVEM SER INTERFACES
///DEVE ESTENDER O JPA REPOSITORY
///CLASSE RESPONSAVEL PELA OPERAÇÕES DE PESQUISA EX.: FINDBYID
@Repository
public interface EstadoRepo extends JpaRepository<Estado, Integer> {
	
	@Transactional(readOnly = true)
	public List<Estado>findAllByOrderByNome();///BUSCA DE ESTADO ORDENADA POR NOME
	
	
	

}

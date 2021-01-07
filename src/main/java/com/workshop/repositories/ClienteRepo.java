package com.workshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.workshop.entitites.Cliente;

///RESPONSAVEL POR PASSAR IR NO BD E BUSCAR OS DADOS
///REPOSITORIOS DEVEM SER INTERFACES
///DEVE ESTENDER O JPA REPOSITORY
///CLASSE RESPONSAVEL PELA OPERAÇÕES DE PESQUISA EX.: FINDBYID
@Repository
public interface ClienteRepo extends JpaRepository<Cliente, Integer> {

	@Transactional(readOnly = true)
	Cliente findByEmail(String email);

	

	

	

}

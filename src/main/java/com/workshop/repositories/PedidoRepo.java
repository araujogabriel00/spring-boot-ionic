package com.workshop.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.workshop.entitites.Cliente;
import com.workshop.entitites.Pedido;

///RESPONSAVEL POR PASSAR IR NO BD E BUSCAR OS DADOS
///REPOSITORIOS DEVEM SER INTERFACES
///DEVE ESTENDER O JPA REPOSITORY
///CLASSE RESPONSAVEL PELA OPERAÇÕES DE PESQUISA EX.: FINDBYID
@Repository
public interface PedidoRepo extends JpaRepository<Pedido, Integer> {

	@Transactional(readOnly = true)
	Page<Pedido> findBycliente(Cliente cliente, Pageable pageRequest);

}

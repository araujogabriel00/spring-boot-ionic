package com.workshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workshop.entitites.Pagamento;

///RESPONSAVEL POR PASSAR IR NO BD E BUSCAR OS DADOS
///REPOSITORIOS DEVEM SER INTERFACES
///DEVE ESTENDER O JPA REPOSITORY
///CLASSE RESPONSAVEL PELA OPERAÇÕES DE PESQUISA EX.: FINDBYID
@Repository
public interface PagamentoRepo extends JpaRepository<Pagamento, Integer> {

}

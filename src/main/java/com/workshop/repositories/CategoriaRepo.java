package com.workshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workshop.entitites.Categoria;

///RESPONSAVEL POR PASSAR ID NO BD E BUSCAR OS DADOS
///REPOSITORIOS DEVEM SER INTERFACES
///DEVE ESTENDER O JPA REPOSITORY
///CLASSE RESPONSAVEL PELA OPERAÇÕES DE PESQUISA EX.: FINDBYID
@Repository
public interface CategoriaRepo extends JpaRepository<Categoria, Integer> {
	
	
	

}

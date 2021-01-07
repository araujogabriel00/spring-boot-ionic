package com.workshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshop.entitites.Cidade;
import com.workshop.repositories.CidadeRepo;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepo repo;
	
	public List<Cidade> findByEstado(Integer estadoId) {
		return repo.findCidades(estadoId);
	}

}

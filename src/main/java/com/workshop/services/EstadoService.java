package com.workshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshop.entitites.Estado;
import com.workshop.repositories.EstadoRepo;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepo estadoRepo;

	public List<Estado> findAll() {
		return estadoRepo.findAllByOrderByNome();
	}

}

package com.workshop.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.workshop.entitites.Cliente;
import com.workshop.repositories.ClienteRepo;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepo clienteRepo;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private EmailService emailService;

	private Random random = new Random();

	public void sendNewPassowrd(String email) throws ObjectNotFoundException {

		Cliente cliente = clienteRepo.findByEmail(email);
		if (cliente == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}

		String newPass = newPassword();
		cliente.setSenha(bCryptPasswordEncoder.encode(newPass));

		clienteRepo.save(cliente);
		emailService.sendNewPasswordEmail(cliente, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = random.nextInt(3);
		if (opt == 0) {// gera um digito
			return (char) (random.nextInt(10) + 48);
		} else if (opt == 1) {// gera letra maiuscula
			return (char) (random.nextInt(26) + 65);

		} else {// gera letra miniscula
			return (char) (random.nextInt(26) + 97);

		}

	}
}

package com.workshop.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.workshop.dto.ClienteNewDTO;
import com.workshop.entitites.Cliente;
import com.workshop.enums.TipoCliente;
import com.workshop.exceptions.FieldMessage;
import com.workshop.repositories.ClienteRepo;
import com.workshop.services.validation.utils.BR;

//VALIDAÇÃO DE INSERÇÃO DE CLIENTE
public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	private ClienteRepo clienteRepo;

	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOUcnpj())) {
			list.add(new FieldMessage("cpfOUcnpj", "CPF inválido"));
		}

		if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDA.getCod()) && !BR.isValidCPF(objDto.getCpfOUcnpj())) {
			list.add(new FieldMessage("cpfOUcnpj", "CNPJ inválido"));
		}

		Cliente aux = clienteRepo.findByEmail(objDto.getEmail());

		if (aux != null) {
			list.add(new FieldMessage("email", "Email já existe"));

		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
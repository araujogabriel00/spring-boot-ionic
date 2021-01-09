package com.workshop.exceptions;

import java.util.ArrayList;
import java.util.List;
//RESPONSAVEL PELOS ERROS DE VALIDAÇÃO
//MOSTRA EM QUAL MOMENTO FOI O ERRO, A MENSAGEM QUE O ERRO RETORNA, ONDE FOI O ERRO
public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
		// TODO Auto-generated constructor stub
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	// ADICIONAR UM ERRO DE CADA VEZ
	public void addError(String FieldName, String message) {
		errors.add(new FieldMessage(FieldName, message));

	}

}

package com.workshop.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandartError {

	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
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

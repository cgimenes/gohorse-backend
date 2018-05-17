package com.xgh.model.command.valueobjects;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = Cpf.class)
public class Cpf implements Document {
	private static final long serialVersionUID = 9088893750584545887L;

	private String cpf;
	
	public Cpf(String cpf) {
		this.cpf = cpf.toString();
		CPFValidator validator = new CPFValidator();
		validator.initialize(null);
		if (!validator.isValid(cpf, null)) {
			throw new IllegalArgumentException("CPF inválido!");
		}
	}

	@Override
	public String toString() {
		return this.cpf.toString();
	}

	public String getValue() {
		return this.cpf;
	}

}

package com.xgh.model.command.valueobjects;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = Cnpj.class)
public class Cnpj implements Document {

	private String cnpj;

	public Cnpj(String cnpj) {
		this.cnpj = cnpj;
		CNPJValidator validator = new CNPJValidator();
		validator.initialize(null);
		if (!validator.isValid(cnpj, null)) {
			throw new IllegalArgumentException("CNPJ inválido!");
		}
	}

	@Override
	public String toString() {
		return this.cnpj.toString();
	}

	public String getValue() {
		return this.cnpj;
	}

}

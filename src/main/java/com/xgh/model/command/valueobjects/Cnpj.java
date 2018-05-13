package com.xgh.model.command.valueobjects;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = Cnpj.class)
public class Cnpj implements Document {
	private static final long serialVersionUID = 9088893750584545887L;

	private String cnpj;

	public Cnpj(@JsonProperty("value") String cnpj) {
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

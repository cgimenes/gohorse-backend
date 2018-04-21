package com.xgh.model.command.valueobjects;

import com.xgh.buildingblocks.valueobject.SingleValueObject;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;

public class CpfCnpj extends SingleValueObject<String> {

	private static final long serialVersionUID = 5778596191517335686L;

	protected CpfCnpj() {}

	public CpfCnpj(String cpf_cnpj) {
		super(cpf_cnpj);

		if (cpf_cnpj.length() > 11) {
			CNPJValidator validator = new CNPJValidator();
			validator.initialize(null);
			if (!validator.isValid(cpf_cnpj, null)) {
				throw new IllegalArgumentException("CNPJ inválido!");
			}
		} else {
			CPFValidator validator = new CPFValidator();
			validator.initialize(null);
			if (!validator.isValid(cpf_cnpj, null)) {
				throw new IllegalArgumentException("CPF inválido!");
			}
		}
	}
}

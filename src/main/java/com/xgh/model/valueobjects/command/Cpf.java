package com.xgh.model.valueobjects.command;

import com.xgh.buildingblocks.valueobject.SingleValueObject;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

public class Cpf extends SingleValueObject<String> {
    private static final long serialVersionUID = 9088893750584545887L;

    protected Cpf() {}

    public Cpf(String cpf) {
        super(cpf);
        if (!new CPFValidator().isValid(cpf, null)) {
            throw new IllegalArgumentException("CPF inválido!");
        }
    }
}

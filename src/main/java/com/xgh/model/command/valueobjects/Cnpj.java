package com.xgh.model.command.valueobjects;

import com.xgh.buildingblocks.valueobject.SingleValueObject;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;

public class Cnpj extends SingleValueObject<String> implements Document {
    public Cnpj(String cnpj) {
        super(cnpj);
        CNPJValidator validator = new CNPJValidator();
        validator.initialize(null);
        if (!validator.isValid(cnpj, null)) {
            throw new IllegalArgumentException("CNPJ inválido!");
        }
    }
}

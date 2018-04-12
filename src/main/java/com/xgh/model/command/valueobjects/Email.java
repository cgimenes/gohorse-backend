package com.xgh.model.command.valueobjects;

import com.xgh.buildingblocks.valueobject.SingleValueObject;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;

public class Email extends SingleValueObject<String>{
    private static final long serialVersionUID = 1455291974507259592L;

    protected Email() {}

    public Email(String email) {
        super(email);
        if (!new EmailValidator().isValid(email, null)) {
            throw new IllegalArgumentException("E-mail inválido");
        }
    }
}
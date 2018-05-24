package com.xgh.model.command.valueobjects;

import com.xgh.buildingblocks.valueobject.SingleValueObject;

public class Sex extends SingleValueObject<Character> {
    protected Sex() {}

    public Sex(String sex) {
        this(sex.charAt(0));
    }

    public Sex(char sex) {
        super(sex);
        if (sex != 'F' && sex != 'M') {
            throw new IllegalArgumentException("Sexo Inválido!");
        }
    }
}

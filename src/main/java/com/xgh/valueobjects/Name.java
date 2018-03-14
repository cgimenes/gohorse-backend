package com.xgh.valueobjects;

import com.xgh.buildingblocks.SingleValueObject;

public class Name extends SingleValueObject<String> {
    public Name(String value) {
        super(value);
        if (value.length() > 100) {
            throw new IllegalArgumentException("O limite máximo para o tamanho de um nome é de 100 caracteres");
        }
    }
}

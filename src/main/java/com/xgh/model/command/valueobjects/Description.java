package com.xgh.model.command.valueobjects;

import com.xgh.buildingblocks.valueobject.SingleValueObject;

public class Description extends SingleValueObject<String> {
    protected Description() {
    }

    public Description(String description) {
        super(description);
        if (description.length() < 1) {
            throw new IllegalArgumentException("É necessário que a descrição possua no mínimo 1 caracter");
        }
        if (description.length() > 100) {
            throw new IllegalArgumentException("O limite máximo para o tamanho de uma descrição é de 100 caracteres");
        }
    }
}

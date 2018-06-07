package com.xgh.model.command.valueobjects;

import com.xgh.buildingblocks.valueobject.SingleValueObject;

public class Phone extends SingleValueObject<String> {
    protected Phone() {
    }

    public Phone(String phone) {
        super(phone);
        if (!phone.matches("[0-9]{10}[0-9]?")) {
            throw new IllegalArgumentException("O telefone deve estar no formato 44999999999");
        }
    }
}

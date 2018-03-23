package com.xgh.valueobjects;

import com.xgh.buildingblocks.SingleValueObject;

public class Phone extends SingleValueObject<String>{

    public Phone(String phone) {
        super(phone);
        if (!phone.matches("0[0-9]{11}")) {
            throw new IllegalArgumentException("O telefone deve estar no formato 044999999999");
        }
    }
}

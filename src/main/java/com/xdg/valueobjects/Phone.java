package com.xdg.valueobjects;

import com.xdg.buildingblocks.SingleValueObject;

public class Phone extends SingleValueObject<String>{

    public Phone(String value) {
        super(value);
        //@todo adicionar regex
//        if (!value.matches("")) {
//            throw new IllegalArgumentException("O telefone deve estar no formato +5544999999999");
//        }
    }
}

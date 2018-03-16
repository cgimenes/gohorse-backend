package com.xgh.valueobjects;

import com.xgh.buildingblocks.SingleValueObject;

public class Phone extends SingleValueObject<String>{

    public Phone(String phone) {
        super(phone);
        //TODO: adicionar regex
//        if (!value.matches("")) {
//            throw new IllegalArgumentException("O telefone deve estar no formato +5544999999999");
//        }
    }
}

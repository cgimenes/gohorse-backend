package com.xgh.valueobjects;

import com.xgh.buildingblocks.SingleValueObject;

public class Phone extends SingleValueObject<String>{
	private static final long serialVersionUID = -9115543650570612238L;

	protected Phone() {}
	
    public Phone(String phone) {
        super(phone);
        if (!phone.matches("0[0-9]{10}[0-9]?")) {
            throw new IllegalArgumentException("O telefone deve estar no formato 044999999999");
        }
    }
}

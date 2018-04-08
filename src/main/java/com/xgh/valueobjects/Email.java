package com.xgh.valueobjects;

import com.xgh.buildingblocks.SingleValueObject;

public class Email extends SingleValueObject<String>{
	private static final long serialVersionUID = 1455291974507259592L;
	
	protected Email() {}
	
	public Email(String email) {
		super(email);
		// TODO: Fazer um regex para validar o email
	}
}

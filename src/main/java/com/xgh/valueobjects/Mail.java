package com.xgh.valueobjects;

import com.xgh.buildingblocks.SingleValueObject;

public class Mail extends SingleValueObject<String>{
	private static final long serialVersionUID = 1455291974507259592L;
	
	public Mail() {
		
	}
	
	public Mail(String mail) {
		// TODO: Fazer um regex para validar o email
	}
}

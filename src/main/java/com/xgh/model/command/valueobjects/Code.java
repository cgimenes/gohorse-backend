package com.xgh.model.command.valueobjects;

import com.xgh.buildingblocks.valueobject.SingleValueObject;

public class Code extends SingleValueObject<String>{
	protected Code() {}
	
	public Code(String code) {
		super(code);
		if(code.length() < 1) {
			throw new IllegalArgumentException("O código deve ter pelo menos 1 caracter!");
		}
		
		if(code.length() > 20) {
			throw new IllegalArgumentException("O código não deve ultrapassar 20 caracteres");
		}
	}
}

package com.xgh.model.command.valueobjects;

import com.xgh.buildingblocks.valueobject.SingleValueObject;

public class Sex extends SingleValueObject<String>{
	private static final long serialVersionUID = -8130975290806456961L;
	
	protected Sex() {}
	
	public Sex(char sex) {
		if(sex != 'F' && sex != 'M') {
			throw new IllegalArgumentException("Sexo Inválido!");
		}
	}
}

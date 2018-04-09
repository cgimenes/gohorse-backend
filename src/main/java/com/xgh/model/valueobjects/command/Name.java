package com.xgh.model.valueobjects.command;

import com.xgh.buildingblocks.valueobject.SingleValueObject;

public class Name extends SingleValueObject<String> {
	private static final long serialVersionUID = 3018741585560237802L;

	protected Name() {}
	
    public Name(String name) {
        super(name);
        if (name.length() < 1) {
            throw new IllegalArgumentException("É necessário que o nome possua no mínimo 1 caracter");
        }
        if (name.length() > 100) {
            throw new IllegalArgumentException("O limite máximo para o tamanho de um nome é de 100 caracteres");
        }
    }
}

package com.xgh.model.command.valueobjects;

import com.xgh.buildingblocks.valueobject.SingleValueObject;

public class DistributionType extends SingleValueObject<String> {

	private static final long serialVersionUID = 6090488518103456243L;

    private String name;

    protected DistributionType() {}

    public DistributionType(String name) {
        
        if (name.length() < 1) {
            throw new IllegalArgumentException("É necessário que o tipo da distribuição possua no mínimo 1 caracter");
        }
        if (name.length() > 100) {
            throw new IllegalArgumentException("O limite máximo para o tamanho de um nome é de 100 caracteres");
        }
        
        this.name = name;
        
        
    }

    public String getName() {
        return name;
    }
}

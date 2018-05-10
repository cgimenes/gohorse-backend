package com.xgh.model.command.valueobjects;

import com.xgh.buildingblocks.valueobject.SingleValueObject;

public class Crmv extends SingleValueObject<String> {
    protected Crmv() {}

    public Crmv(String crmv) {
        super(crmv);
        // TODO: Validar qual é a composição do CRMV
    }
}

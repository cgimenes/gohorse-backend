package com.xgh.model.command.valueobjects;

import com.xgh.buildingblocks.valueobject.SingleValueObject;

import java.time.LocalDate;

// TODO excluir essa classe
public class Date extends SingleValueObject<LocalDate> {
    protected Date() {
        super();
    }

    public Date(String date) {
        super(LocalDate.parse(date));
    }

    public Date(LocalDate date) {
        super(date);
    }
}

package com.xdg.xdg.laboratory;

import com.xdg.valueobjects.Id;

import java.util.UUID;

public class LaboratoryId extends Id<Laboratory> {
    public LaboratoryId(UUID value) {
        super(value);
    }

    //@todo passar para a classe base
    public static LaboratoryId generate() {
        return new LaboratoryId(UUID.randomUUID());
    }
}

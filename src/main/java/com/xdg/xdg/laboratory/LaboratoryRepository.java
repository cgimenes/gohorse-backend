package com.xdg.xdg.laboratory;

import com.xdg.buildingblocks.Repository;
import com.xdg.valueobjects.Name;
import com.xdg.valueobjects.Phone;

public final class LaboratoryRepository extends Repository<LaboratoryId, Laboratory> {
    @Override
    public Laboratory findById(LaboratoryId id) {
        connection.query()

        return new Laboratory(
                id,
                new Name("meteoloko"),
                new Phone("tika")
        );
    }

    @Override
    public void insert(Laboratory laboratory) {
        return;
    }

    @Override
    public void update(Laboratory laboratory) {
        return;
    }
}

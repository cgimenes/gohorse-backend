package com.xgh.model.command.operational.appointment;

import com.xgh.buildingblocks.entity.EntityId;
import java.util.UUID;

public class AppointmentId extends EntityId {
    public AppointmentId() {
        super();
    }

    public AppointmentId(String value) {
        super(value);
    }

    public AppointmentId(UUID value) {
        super(value);
    }
}


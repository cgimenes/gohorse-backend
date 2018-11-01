package com.xgh.model.command.operational.appointment;

import com.xgh.model.command.operational.valueobjects.OperationId;
import java.util.UUID;

public class AppointmentId extends OperationId {
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


package com.xgh.model.command.operational.appointment.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.model.command.operational.Priceable;
import com.xgh.model.command.operational.appointment.AppointmentId;
import java.math.BigDecimal;

public class AppointmentWasFinished extends EntityEvent<AppointmentId> implements Priceable {
    private BigDecimal price;

    protected AppointmentWasFinished() {
    }

    public AppointmentWasFinished(AppointmentId id, BigDecimal price, EntityVersion version) {
        super(id, version);
        this.price = price;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }
}

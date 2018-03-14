package com.xgh.buildingblocks;

import java.util.Calendar;

public abstract class Event {

    private final Calendar ocurredOn;

    public Event() {
        this.ocurredOn = Calendar.getInstance();
    }

    public Calendar getOcurredOn() {
        return ocurredOn;
    }
}

package com.xgh.buildingblocks;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xgh.valueobjects.Id;

public abstract class Event<IdType extends Id> extends ValueObject {

	@JsonIgnore
    private final Calendar ocurredOn;
	@JsonIgnore
    protected final IdType entityId;
	@JsonIgnore
	private EntityVersion entityVersion;

    public Event(IdType entityId, EntityVersion entityVersion) {
    	this.entityId = entityId;
		this.entityVersion = entityVersion;
        this.ocurredOn = Calendar.getInstance();
    }

    public IdType getEntityId() {
        return this.entityId;
    }

    public Calendar getOcurredOn() {
        return ocurredOn;
    }

	public EntityVersion getEntityVersion() {
		return entityVersion;
	}
	
	public String getType() {
		return this.getClass().getSimpleName();
	}
}

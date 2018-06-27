package com.xgh.model.command.valueobjects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.xgh.buildingblocks.valueobject.ValueObject;

@JsonDeserialize(using = CpfCnpjDeserializer.class)
public interface Document extends ValueObject{

	public abstract String toString();

	public abstract String getValue();
}
package com.xgh.buildingblocks;

import java.io.IOException;
import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class ValueObject implements Serializable {
	private static final long serialVersionUID = -272564284742728433L;

	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(String.format(
				"Falha ao serializar objeto do tipo: %s", this.getClass().getName()), e);
		}
	}
	
	public static ValueObject fromString(String type, String data) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return (ValueObject) objectMapper.readValue(data, Class.forName(type));
		} catch (ClassNotFoundException | IOException e) {
			throw new RuntimeException(String.format(
					"Falha ao deserializar objeto do tipo: %s, com os dados: ", type, data), e);
		}
	}
}

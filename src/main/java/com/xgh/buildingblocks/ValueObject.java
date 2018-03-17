package com.xgh.buildingblocks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class ValueObject {
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(String.format(
				"Falha ao serializar objeto do tipo: %s", this.getClass().getName()));
		}
	}
}

package com.xgh.buildingblocks;

import java.io.IOException;
import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public abstract class ValueObject implements Serializable {
	private static final long serialVersionUID = -272564284742728433L;

	/*
	 * Serializa o value object em JSON
	 */
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(String.format(
				"Falha ao serializar objeto do tipo: %s", this.getClass().getName()), e);
		}
	}
	
	/*
	 * Deserializa o value object à partir de um JSON
	 */
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

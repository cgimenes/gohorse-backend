package com.xgh;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public interface JsonSerializable extends Serializable {
	/*
	 * Serializa o value object em JSON
	 */
	default public String toJson() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(String.format(
				"Falha ao serializar objeto do tipo: %s", this.getClass().getName()), e);
		}
	}
}

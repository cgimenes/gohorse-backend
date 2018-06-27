package com.xgh.model.command.valueobjects;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CpfCnpjDeserializer extends JsonDeserializer<Document> {
	@Override
	public Document deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		ObjectMapper mapper = (ObjectMapper) jp.getCodec();
		 JsonNode root = mapper.readTree(jp);

		Class<? extends Document> instanceClass = null;

		instanceClass = Cpf.class;

		if (root.asText().length() > 11) {
			instanceClass = Cnpj.class;
		}

		return mapper.readValue(root.toString(), instanceClass);
	}
}
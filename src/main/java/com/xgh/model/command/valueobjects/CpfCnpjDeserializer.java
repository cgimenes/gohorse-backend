package com.xgh.model.command.valueobjects;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class CpfCnpjDeserializer extends JsonDeserializer<Document> {
	@Override
	public Document deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		ObjectMapper mapper = (ObjectMapper) jp.getCodec();
		ObjectNode root = (ObjectNode) mapper.readTree(jp);

		Class<? extends Document> instanceClass = null;

		instanceClass = Cpf.class;

		if (root.get("value").size() > 11) {
			instanceClass = Cnpj.class;
		}

		return mapper.convertValue(root, instanceClass);
	}
}
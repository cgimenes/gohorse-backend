package com.xgh.model.command.operational.valueobjects;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;

public class DocumentDeserializer extends JsonDeserializer<Document> {
    @Override
    public Document deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        String value = node.asText();
        if (value.length() > 11) {
            return new Cnpj(value);
        }
        return new Cpf(value);
    }
}

package com.xgh.model.command.operational.valueobjects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = DocumentDeserializer.class)
public interface Document {
    String toString();

    String getValue();
}
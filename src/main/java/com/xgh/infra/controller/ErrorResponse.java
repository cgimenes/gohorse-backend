package com.xgh.infra.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ErrorResponse implements Serializable {
    private static final long serialVersionUID = -2168773266239620472L;

    private final int errorCode;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final Map<String, String> additionalInformations;

    public ErrorResponse(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
        this.additionalInformations = new HashMap<>();
    }

    public ErrorResponse(int errorCode, String message, Map<String, String> additionalInformations) {
        this(errorCode, message);
        this.additionalInformations.putAll(additionalInformations);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, String> getAdditionalInformations() {
        return additionalInformations;
    }
}

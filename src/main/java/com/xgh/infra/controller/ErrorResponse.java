package com.xgh.infra.controller;

import java.io.Serializable;

public class ErrorResponse implements Serializable {
    private static final long serialVersionUID = -2168773266239620472L;

    private final int errorCode;
    private final String message;

    public ErrorResponse(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}

package com.xgh.exceptions;

public class InvalidArgumentException extends RuntimeException {
	private static final long serialVersionUID = 3940676470123095148L;

	public InvalidArgumentException(String field, String reason) {
		super(String.format("O valor preenchido no campo '%s' é inválido. Motivo: '%s'", field, reason));
	}
}

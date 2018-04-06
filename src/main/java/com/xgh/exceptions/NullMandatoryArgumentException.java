package com.xgh.exceptions;

/*
 * Exceção disparada quando é passado nulo para um argumento obrigatório 
 */
public class NullMandatoryArgumentException extends RuntimeException {
	private static final long serialVersionUID = 4655238758476367545L;
	
	public NullMandatoryArgumentException(String field) {
		super(String.format("O campo %s é obrigatório", field));
	}
}

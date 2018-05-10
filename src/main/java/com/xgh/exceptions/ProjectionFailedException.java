package com.xgh.exceptions;

/*
 * Exceção disparada quando uma dada entidade não é encontrada no Query Model e era para a mesma existir
 * TODO: adicionar rotina de retentativa na projeção
 */
public class ProjectionFailedException extends RuntimeException {
    private static final long serialVersionUID = -5295547601146046528L;

    public ProjectionFailedException(String entityName) {
        super(String.format("Falha na projeção da entidade '%s'", entityName));
    }
}

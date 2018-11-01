package com.xgh.exceptions;

import java.util.UUID;


/*
 * Exceção disparada quando uma dada entidade não é encontrada
 */
public class EntityNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -2789472039867954508L;

    public EntityNotFoundException(String entityName, UUID id) {
        super(String.format("Entidade '%s' não encontrada para o ID '%s'", entityName, id.toString()));
    }
}

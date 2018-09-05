package com.xgh.exceptions;

import com.xgh.buildingblocks.entity.AggregateRoot;

/*
 * Exceção disparada quando um campo de uma dada entidade possui uma validação
 * de conflito (ex.: duplicidade)
 */
public class EntityFieldConflictedException extends RuntimeException {
    private static final long serialVersionUID = -2789472039867954508L;

    private String fieldName;

    public EntityFieldConflictedException(Class<? extends AggregateRoot> entity, String fieldName) {
        super(String.format("Conflito no campo '%s' da entidade '%s'", fieldName, entity.getSimpleName()));
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}

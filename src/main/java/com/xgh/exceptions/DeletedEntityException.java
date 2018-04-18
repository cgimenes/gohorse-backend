package com.xgh.exceptions;

/*
 * Exceção disparada quando é solicitada uma alteração em uma entidade que estava marcada como deletada
 */
public class DeletedEntityException extends RuntimeException {
    private static final long serialVersionUID = -654700379959769169L;
}

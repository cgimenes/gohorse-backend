package com.xgh.infra.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.xgh.exceptions.DeletedEntityException;
import com.xgh.exceptions.EntityFieldConflictedException;
import com.xgh.exceptions.EntityNotFoundException;
import com.xgh.exceptions.InvalidArgumentException;
import com.xgh.exceptions.NullMandatoryArgumentException;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ExceptionManager {
    private final Logger logger = LogManager.getLogger(this.getClass());

    /*
     * Códigos de erro
     */
    private static final int INTERNAL_ERROR = 1;
    private static final int ENTITY_NOT_FOUND = 2;
    private static final int BAD_REQUEST = 3;
    private static final int TYPE_MISMATCH = 4;
    private static final int ROUTE_NOT_FOUND = 5;
    private static final int INVALID_ARGUMENT = 6;
    private static final int UNPROCESSABLE_ENTITY = 7;

    /*
     * Exception Handlers
     */
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    public @ResponseBody
    ErrorResponse handleException(Exception ex) {
        logger.fatal(ex);

        return new ErrorResponse(INTERNAL_ERROR, "Erro interno não tratado");
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler({EntityNotFoundException.class})
    public @ResponseBody
    ErrorResponse handleException(EntityNotFoundException ex) {
        logger.info(ex);

        return new ErrorResponse(ENTITY_NOT_FOUND, ex.getMessage());
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler({javax.persistence.EntityNotFoundException.class})
    public @ResponseBody
    ErrorResponse handleException(javax.persistence.EntityNotFoundException ex) {
        logger.info(ex);

        return new ErrorResponse(ENTITY_NOT_FOUND, "Entidade não encontrada");
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler({DeletedEntityException.class})
    public @ResponseBody
    ErrorResponse handleException(DeletedEntityException ex) {
        logger.info(ex);

        return new ErrorResponse(ENTITY_NOT_FOUND, "Entidade não encontrada");
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageConversionException.class})
    public @ResponseBody
    ErrorResponse handleException(HttpMessageConversionException ex) {
        if (ex.getCause() == null) {
            return handleException((Exception) ex);
        }

        if (ex.getCause() instanceof JsonParseException) {
            return new ErrorResponse(BAD_REQUEST, "Falha ao parsear o JSON da mensagem");
        }

        if (ex.getCause().getCause() == null) {
            return handleException((Exception) ex);
        }

        logger.info(ex);
        return new ErrorResponse(BAD_REQUEST, ex.getCause().getCause().getMessage());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({NullMandatoryArgumentException.class})
    public @ResponseBody
    ErrorResponse handleException(NullMandatoryArgumentException ex) {
        logger.info(ex);

        return new ErrorResponse(BAD_REQUEST, ex.getMessage());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({EntityFieldConflictedException.class})
    public @ResponseBody
    ErrorResponse handleException(EntityFieldConflictedException ex) {
        logger.info(ex);

        return new ErrorResponse(BAD_REQUEST, ex.getMessage(), new HashMap<String, String>() {
            private static final long serialVersionUID = 1241756225957278590L;
            {
                put("conflictedField", ex.getFieldName());
            }
        });
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public @ResponseBody
    ErrorResponse handleException(MethodArgumentTypeMismatchException ex) {
        logger.info(ex);

        return new ErrorResponse(TYPE_MISMATCH, String.format(
                "Tipo inválido para o valor: '%s', esperado: '%s'",
                ex.getValue(),
                ex.getRequiredType()));
    }
    
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidArgumentException.class})
    public @ResponseBody
    ErrorResponse handleException(InvalidArgumentException ex) {
        logger.info(ex);

        return new ErrorResponse(INVALID_ARGUMENT, ex.getMessage());
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public @ResponseBody
    ErrorResponse handleException(HttpRequestMethodNotSupportedException ex) {
        logger.info(ex);

        return new ErrorResponse(ROUTE_NOT_FOUND, "Rota não encontrada");
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public @ResponseBody
    ErrorResponse handleException(HttpMessageNotReadableException ex) {
        logger.info(ex);

        return new ErrorResponse(BAD_REQUEST, "Falha ao ler o corpo da mensagem");
    }

    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({IllegalStateException.class})
    public @ResponseBody
    ErrorResponse handleException(IllegalStateException ex) {
        logger.info(ex);

        return new ErrorResponse(UNPROCESSABLE_ENTITY, "Estado atual da entidade não suporta essa transição");
    }

}

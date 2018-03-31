package com.xgh.infra.controller;

import org.apache.logging.log4j.LogManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.xgh.exceptions.DeletedEntityException;
import com.xgh.exceptions.EntityNotFoundException;
import com.xgh.exceptions.NullMandatoryArgumentException;

@ControllerAdvice
public class ExceptionManager {
	// TODO usar constantes nos código de erro
	
	@ResponseStatus(code=HttpStatus.NOT_FOUND)
	@ExceptionHandler({ EntityNotFoundException.class })
	public @ResponseBody ErrorResponse handleException(EntityNotFoundException ex) {
		LogManager.getRootLogger().info("ExceptionManager", ex);
		
		return new ErrorResponse(2, "Entidade não encontrada");
	}
	
	@ResponseStatus(code=HttpStatus.NOT_FOUND)
	@ExceptionHandler({ DeletedEntityException.class })
	public @ResponseBody ErrorResponse handleException(DeletedEntityException ex) {
		LogManager.getRootLogger().info("ExceptionManager", ex);
		
		return new ErrorResponse(2, "Entidade não encontrada");
	}

	@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ Exception.class })
	public @ResponseBody ErrorResponse handleException(Exception ex) {
		LogManager.getRootLogger().fatal("ExceptionManager", ex);
		
		return new ErrorResponse(1, "Erro interno não tratado");
	}
	
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ HttpMessageConversionException.class })
	public @ResponseBody ErrorResponse handleException(HttpMessageConversionException ex) {
		LogManager.getRootLogger().info("ExceptionManager", ex);
		
		return new ErrorResponse(3, ex.getCause().getCause().getMessage());
	}
	
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ NullMandatoryArgumentException.class })
	public @ResponseBody ErrorResponse handleException(NullMandatoryArgumentException ex) {
		LogManager.getRootLogger().info("ExceptionManager", ex);
		
		return new ErrorResponse(3, ex.getMessage());
	}
	
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public @ResponseBody ErrorResponse handleException(MethodArgumentTypeMismatchException ex) {
		LogManager.getRootLogger().info("ExceptionManager", ex);
		
		return new ErrorResponse(4, ex.getMessage());
	}	
	
	@ResponseStatus(code=HttpStatus.NOT_FOUND)
	@ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
	public @ResponseBody ErrorResponse handleException(HttpRequestMethodNotSupportedException ex) {
		LogManager.getRootLogger().info("ExceptionManager", ex);
		
		return new ErrorResponse(5, "Rota não encontrada");
	}	
}

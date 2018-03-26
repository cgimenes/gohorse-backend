package com.xgh.infra;

import org.apache.logging.log4j.LogManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.xgh.exceptions.EntityNotFoundException;

@ControllerAdvice
public class ExceptionManager {

	@ResponseStatus(code=HttpStatus.NOT_FOUND)
	@ExceptionHandler({ EntityNotFoundException.class })
	public @ResponseBody ErrorResponse handleException(EntityNotFoundException ex) {
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
}

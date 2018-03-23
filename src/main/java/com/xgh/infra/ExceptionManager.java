package com.xgh.infra;

import org.springframework.http.HttpStatus;
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
		return new ErrorResponse(2, "Entidade não encontrada");
	}

	@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ Exception.class })
	public @ResponseBody ErrorResponse handleException(Exception ex) {
		// TODO: logar
		return new ErrorResponse(1, "Erro interno não tratado");
	}
}

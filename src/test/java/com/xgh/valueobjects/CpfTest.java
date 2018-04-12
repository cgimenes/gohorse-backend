package com.xgh.valueobjects;

import static org.junit.Assert.*;

import org.junit.Test;

import com.xgh.model.valueobjects.command.Cpf;

public class CpfTest {

	@Test
	public void test() {
		Cpf cpf = new Cpf();
		assertTrue("O CPF informado não é valido!",cpf.isValid("09450600929"));
		assertFalse("O CPF informado é válido!",cpf.isValid("09450600928"));
	}

}

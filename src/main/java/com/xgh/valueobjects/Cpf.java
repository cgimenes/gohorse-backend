package com.xgh.valueobjects;

import com.xgh.buildingblocks.SingleValueObject;

public class Cpf extends SingleValueObject<String> {
	private static final long serialVersionUID = 9088893750584545887L;

	public Cpf() {

	}

	public Cpf(String cpf) {
		super(cpf);
		if (!isValid(cpf)) {
			throw new IllegalArgumentException("CPF inválido!");
		}
	}

	public boolean isValid(String cpf) {
		if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222") || 
				cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555") || 
				cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888") || 
				cpf.equals("99999999999") || (cpf.length() != 11)) {

			return false;
		}
		char digito10, digito11;
		int sm, i, r, num, peso;

		try {
		//calcular 1o digito do cpf
			sm = 0;
			peso = 10;
			
			for(i = 0;i < 9; i++) {
				num = (int)(cpf.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}
			
			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11)) {
				digito10 = '0';
			} else {
				digito10 = (char)(r + 48);
			}
			
		//calcular 2o digito do cpf
			sm = 0;
			peso = 11;
			
			for(i = 0; i < 10; i++) {
				num = (int)(cpf.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}
			
			r = 11 - (sm % 11);
			if((r == 10) || (r == 11)) {
				digito11 = '0';				
			} else {
				digito11 = (char)(r + 48);
			}
			
			return ((digito10 == cpf.charAt(9)) && (digito11 == cpf.charAt(10))) ? true : false;

		} catch (Exception erro) {
			return false;
		}
	}
}

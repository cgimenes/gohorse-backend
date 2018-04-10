package com.xgh.model.valueobjects;

import com.xgh.buildingblocks.valueobject.ValueObject;
import com.xgh.exceptions.NullMandatoryArgumentException;

public class Address implements ValueObject {
	private static final long serialVersionUID = 2642148787979337627L;

	private PostalCode postalCode;
	private Integer number;
	private String complement;
	
	protected Address() {}
	
	public Address(PostalCode postalCode, Integer number, String complement) {
		if (postalCode == null) {
    		throw new NullMandatoryArgumentException("código postal");
		}
		
		if (number == null) {
    		throw new NullMandatoryArgumentException("número");
		}
		
		this.postalCode = postalCode;
		this.number = number;
		this.complement = complement;
	}
	
	public PostalCode getPostalCode() {
		return postalCode;
	}
		
	public Integer getNumber() {
		return number;
	}
	
	public String getComplement() {
		return complement;
	}
}

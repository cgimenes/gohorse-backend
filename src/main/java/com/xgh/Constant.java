package com.xgh;

// TODO rever essa solução
public enum Constant {
	PAGE_SIZE(10);

	Constant(Object value) {
		this.value = value;
	}

	private final Object value;
	
	public Object getValue() {
		return value;
	}
	
	public Integer asInteger() {
		return (Integer)value;
	}
}

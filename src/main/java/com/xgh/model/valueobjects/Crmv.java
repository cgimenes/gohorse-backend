package com.xgh.model.valueobjects;

import com.xgh.buildingblocks.valueobject.SingleValueObject;

public class Crmv extends SingleValueObject<String> {
	private static final long serialVersionUID = -3643528627692820569L;

	protected Crmv() {}

	public Crmv(String crmv) {
		super(crmv);
		// TODO: Validar qual é a composição do CRMV
	}
}

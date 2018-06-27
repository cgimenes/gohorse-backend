package com.xgh.model.command.supplier.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.supplier.SupplierId;

public class SupplierWasDeleted extends Event<SupplierId>{
	private static final long serialVersionUID = 5568983303126584553L;
	
	protected SupplierWasDeleted() {}
	
	public SupplierWasDeleted(SupplierId id, EntityVersion version) {
		super(id,version);
	}
}

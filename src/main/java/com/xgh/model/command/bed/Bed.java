package com.xgh.model.command.bed;

import com.xgh.buildingblocks.entity.AggregateRoot;
import com.xgh.exceptions.NullMandatoryArgumentException;
import com.xgh.model.command.bed.events.BedWasDeleted;
import com.xgh.model.command.bed.events.BedWasRegistered;
import com.xgh.model.command.bed.events.BedWasUpdated;
import com.xgh.model.command.valueobjects.Code;


public class Bed extends AggregateRoot<BedId>{

	private static final long serialVersionUID = -7513301137861578705L;
	

	private Code code;
	
	public Bed() {
		super();
	}

	public void register(BedId id, Code code) {
    	if (id == null) {
    		throw new NullMandatoryArgumentException("id");
    	}
		
		if (code == null) {
			throw new NullMandatoryArgumentException("code");
		}

		

		recordAndApply(new BedWasRegistered(id, code, this.nextVersion()));
	}

	public void update(Code code) {
		recordAndApply(new BedWasUpdated(this.id, code, this.nextVersion()));
	}
	
	public void delete() {
		recordAndApply(new BedWasDeleted(this.id, this.nextVersion()));
	}

	protected void when(BedWasRegistered event) {
		this.code = event.getCode();
	}

	protected void when(BedWasUpdated event) {
		this.code = event.getCode();
	}
	
	protected void when(BedWasDeleted event) {
		this.markDeleted();
	}

	public Code getCode() {
		return code;
	}


}

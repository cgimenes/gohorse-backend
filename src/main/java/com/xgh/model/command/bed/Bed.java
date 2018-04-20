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
	private Boolean busy;
	
	public Bed() {
		super();
	}

	public void register(BedId id, Code code, Boolean busy) {
    	if (id == null) {
    		throw new NullMandatoryArgumentException("id");
    	}
		
		if (code == null) {
			throw new NullMandatoryArgumentException("code");
		}

		if (busy == null) {
			throw new NullMandatoryArgumentException("busy");
		}

		recordAndApply(new BedWasRegistered(id, code, busy, this.nextVersion()));
	}

	public void update(Code code, Boolean busy) {
		recordAndApply(new BedWasUpdated(this.id, code, busy, this.nextVersion()));
	}
	
	public void delete() {
		recordAndApply(new BedWasDeleted(this.id, this.nextVersion()));
	}

	protected void when(BedWasRegistered event) {
		this.code = event.getCode();
		this.busy = event.getBusy();
	}

	protected void when(BedWasUpdated event) {
		this.code = event.getCode();
		this.busy = event.getBusy();
	}
	
	protected void when(BedWasDeleted event) {
		this.markDeleted();
	}

	public Code getCode() {
		return code;
	}

	public Boolean getBusy() {
		return busy;
	}
}

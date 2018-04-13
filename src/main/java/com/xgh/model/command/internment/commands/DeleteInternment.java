package com.xgh.model.command.internment.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.internment.InternmentId;

public class DeleteInternment implements Command {
	private static final long serialVersionUID = 8422104097563357561L;

	InternmentId id;

	public DeleteInternment() {
		this.id = null;
	}

	public InternmentId getId() {
		return this.id;
	}
}

package com.xgh.model.command.operational.surgery.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.exceptions.EntityFieldConflictedException;
import com.xgh.model.command.operational.surgery.Surgery;
import com.xgh.model.command.operational.surgery.commands.UpdateSurgery;
import com.xgh.model.query.operational.surgery.SurgeryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SurgeryUpdate implements CommandHandler<UpdateSurgery> {
	private final EventStore eventStore;
	private final SurgeryRepository repository;

	@Autowired
	public SurgeryUpdate(EventStore eventStore, SurgeryRepository repository) {
		this.eventStore = eventStore;
		this.repository = repository;
	}

	@Override
	public void execute(UpdateSurgery command) {
		if (repository.existsByDateTime(command.getDateTime())) {
			throw new EntityFieldConflictedException(Surgery.class, "dateTime");
		}

		Surgery surgery = eventStore.pull(Surgery.class, command.getId());
		surgery.update(command.getVeterinary(), command.getDateTime(), command.getSurgeryType(), command.getNotes());
		eventStore.push(surgery);
	}
}

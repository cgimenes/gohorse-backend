package com.xgh.model.command.operational.surgery.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.exceptions.EntityFieldConflictedException;
import com.xgh.model.command.operational.surgery.Surgery;
import com.xgh.model.command.operational.surgery.commands.RegisterSurgery;
import com.xgh.model.query.operational.surgery.SurgeryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SurgeryRegistration implements CommandHandler<RegisterSurgery> {
	private final EventStore eventStore;
	private final SurgeryRepository repository;

	@Autowired
	public SurgeryRegistration(EventStore eventStore, SurgeryRepository repository) {
		this.eventStore = eventStore;
		this.repository = repository;
	}

	@Override
	public void execute(RegisterSurgery command) {
		if (repository.existsByDateTime(command.getDateTime())) {
			throw new EntityFieldConflictedException(Surgery.class, "dateTime");
		}
		
		Surgery surgery = new Surgery();
		surgery.register(command.getId(), command.getAnimal(), command.getVeterinary(), command.getDateTime(),
				command.getSurgeryType(), command.getNotes(), command.getAppointment());
		eventStore.push(surgery);
	}
}

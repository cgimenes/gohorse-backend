package com.xgh.eventhandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.xgh.buildingblocks.Event;
import com.xgh.buildingblocks.EventHandler;
import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.xgh.veterinary.command.Veterinary;
import com.xgh.xgh.veterinary.command.events.VeterinaryWasDeleted;
import com.xgh.xgh.veterinary.command.events.VeterinaryWasRegistered;
import com.xgh.xgh.veterinary.command.events.VeterinaryWasUpdated;
import com.xgh.xgh.veterinary.query.VeterinaryQueryRepository;

@Component
public class VeterinaryProjector implements EventHandler {

	@Autowired
	private PostgresEventStore eventStore;
	
	@Autowired
	private VeterinaryQueryRepository repository;
	
	@Override
	public boolean isSubscribedTo(Event<?> event) {
		return event instanceof VeterinaryWasDeleted
			|| event instanceof VeterinaryWasRegistered
			|| event instanceof VeterinaryWasUpdated;
	}

	@Async
	@Override
	public void execute(Event<?> event) {
		Veterinary entity = eventStore.pull(Veterinary.class, event.getEntityId());
		
		com.xgh.xgh.veterinary.query.Veterinary projection = new com.xgh.xgh.veterinary.query.Veterinary(
				entity.getId().getValue(),
				entity.getName().getValue(),
				entity.getPhone().getValue(),
				entity.getCrmv().getValue(),
				entity.getEmail().getValue(),
				entity.getBirthDate(),
				entity.isDeleted());
		
		repository.save(projection);
	}

}

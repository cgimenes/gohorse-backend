package com.xgh.eventhandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xgh.buildingblocks.event.Event;
import com.xgh.buildingblocks.event.EventHandler;
import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.model.address.query.AddressProjector;
import com.xgh.model.veterinary.command.Veterinary;
import com.xgh.model.veterinary.command.events.VeterinaryWasDeleted;
import com.xgh.model.veterinary.command.events.VeterinaryWasRegistered;
import com.xgh.model.veterinary.command.events.VeterinaryWasUpdated;
import com.xgh.model.veterinary.query.VeterinaryRepository;

@Component
public class VeterinaryProjector implements EventHandler {

	@Autowired
	private PostgresEventStore eventStore;
	
	@Autowired
	private VeterinaryRepository repository;
	
	@Autowired
	private AddressProjector addressProjector;
	
	@Override
	public boolean isSubscribedTo(Event<?> event) {
		return event instanceof VeterinaryWasDeleted
			|| event instanceof VeterinaryWasRegistered
			|| event instanceof VeterinaryWasUpdated;
	}

	@Override
	public void execute(Event<?> event) {
		Veterinary entity = eventStore.pull(Veterinary.class, event.getEntityId());

		com.xgh.model.address.query.Address addressProjection = addressProjector.execute(entity.getAddress());
		
		com.xgh.model.veterinary.query.Veterinary projection = new com.xgh.model.veterinary.query.Veterinary(
				entity.getId().getValue(),
				entity.getName().getValue(),
				addressProjection,
				entity.getPhone().getValue(),
				entity.getCrmv().getValue(),
				entity.getEmail() == null ? null : entity.getEmail().getValue(),
				entity.getBirthDate(),
				entity.isDeleted());
		
		repository.save(projection);
	}

}

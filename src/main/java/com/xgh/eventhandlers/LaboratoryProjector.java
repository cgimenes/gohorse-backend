package com.xgh.eventhandlers;

import com.xgh.model.query.address.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xgh.buildingblocks.event.Event;
import com.xgh.buildingblocks.event.EventHandler;
import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.model.query.address.AddressProjector;
import com.xgh.model.command.laboratory.Laboratory;
import com.xgh.model.command.laboratory.events.LaboratoryWasDeleted;
import com.xgh.model.command.laboratory.events.LaboratoryWasRegistered;
import com.xgh.model.command.laboratory.events.LaboratoryWasUpdated;
import com.xgh.model.query.laboratory.LaboratoryRepository;

@Component
public class LaboratoryProjector implements EventHandler {

	@Autowired
	private PostgresEventStore eventStore;
	
	@Autowired
	private LaboratoryRepository laboratoryRepository;
	
	@Autowired
	private AddressProjector addressProjector;
	
	@Override
	public boolean isSubscribedTo(Event<?> event) {
		return event instanceof LaboratoryWasDeleted
			|| event instanceof LaboratoryWasRegistered
			|| event instanceof LaboratoryWasUpdated;
	}

	@Override
	public void execute(Event<?> event) {
		Laboratory entity = eventStore.pull(Laboratory.class, event.getEntityId());

		Address addressProjection = addressProjector.execute(entity.getAddress());
		
		com.xgh.model.query.laboratory.Laboratory laboratoryProjection = new com.xgh.model.query.laboratory.Laboratory(
				entity.getId().getValue(),
				entity.getCompanyName().getValue(),
				entity.getPhone().getValue(),
				addressProjection,
				entity.isDeleted());
		
		laboratoryRepository.save(laboratoryProjection);
	}

}

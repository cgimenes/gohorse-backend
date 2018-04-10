package com.xgh.infra.eventhandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xgh.buildingblocks.event.Event;
import com.xgh.buildingblocks.event.EventHandler;
import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.model.address.query.AddressProjector;
import com.xgh.model.laboratory.command.Laboratory;
import com.xgh.model.laboratory.command.events.LaboratoryWasDeleted;
import com.xgh.model.laboratory.command.events.LaboratoryWasRegistered;
import com.xgh.model.laboratory.command.events.LaboratoryWasUpdated;
import com.xgh.model.laboratory.query.LaboratoryRepository;

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

		com.xgh.model.address.query.Address addressProjection = addressProjector.execute(entity.getAddress());
		
		com.xgh.model.laboratory.query.Laboratory laboratoryProjection = new com.xgh.model.laboratory.query.Laboratory(
				entity.getId().getValue(),
				entity.getCompanyName().getValue(),
				entity.getPhone().getValue(),
				addressProjection,
				entity.isDeleted());
		
		laboratoryRepository.save(laboratoryProjection);
	}

}

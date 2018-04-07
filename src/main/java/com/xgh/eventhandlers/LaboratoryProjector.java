package com.xgh.eventhandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.xgh.buildingblocks.Event;
import com.xgh.buildingblocks.EventHandler;
import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.xgh.laboratory.command.Laboratory;
import com.xgh.xgh.laboratory.command.events.LaboratoryWasDeleted;
import com.xgh.xgh.laboratory.command.events.LaboratoryWasRegistered;
import com.xgh.xgh.laboratory.command.events.LaboratoryWasUpdated;
import com.xgh.xgh.laboratory.query.LaboratoryQueryRepository;

@Component
public class LaboratoryProjector implements EventHandler {

	@Autowired
	private PostgresEventStore eventStore;
	
	@Autowired
	private LaboratoryQueryRepository repository;
	
	@Override
	public boolean isSubscribedTo(Event<?> event) {
		return event instanceof LaboratoryWasDeleted
			|| event instanceof LaboratoryWasRegistered
			|| event instanceof LaboratoryWasUpdated;
	}

	@Async
	@Override
	public void execute(Event<?> event) {
		Laboratory entity = eventStore.pull(Laboratory.class, event.getEntityId());
		
		com.xgh.xgh.laboratory.query.Laboratory projection = new com.xgh.xgh.laboratory.query.Laboratory(
				entity.getId().getValue(),
				entity.getCompanyName().getValue(),
				entity.getPhone().getValue(),
				entity.isDeleted());
		
		repository.save(projection);
	}

}

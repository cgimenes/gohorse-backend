package com.xgh.eventhandlers;

import com.xgh.model.query.bed.BedRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xgh.buildingblocks.event.Event;
import com.xgh.buildingblocks.event.EventHandler;
import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.model.command.bed.Bed;
import com.xgh.model.command.bed.events.BedWasDeleted;
import com.xgh.model.command.bed.events.BedWasRegistered;
import com.xgh.model.command.bed.events.BedWasUpdated;


@Component
public class BedProjector implements EventHandler{
	
	@Autowired
	private PostgresEventStore eventStore;
	
	@Autowired
	private BedRepository repository;
	
	
	@Override
	public boolean isSubscribedTo(Event<?> event) {
		return event instanceof BedWasDeleted
			|| event instanceof BedWasRegistered
			|| event instanceof BedWasUpdated;
	}

	@Override
	public void execute(Event<?> event) {
		Bed entity = eventStore.pull(Bed.class, event.getEntityId());

		
		com.xgh.model.query.bed.Bed projection = new com.xgh.model.query.bed.Bed(
				entity.getId().getValue(),
				entity.getCode().getValue(),
				entity.isBusy(),
				entity.isDeleted());
		
		repository.save(projection);
	}

}

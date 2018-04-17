package com.xgh.eventhandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xgh.buildingblocks.event.Event;
import com.xgh.buildingblocks.event.EventHandler;
import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.model.command.internment.Internment;
import com.xgh.model.command.internment.events.InternmentWasDeleted;
import com.xgh.model.command.internment.events.InternmentWasRegistered;
import com.xgh.model.command.internment.events.InternmentWasUpdated;
import com.xgh.model.query.internment.InternmentRepository;

@Component
public class InternmentProjector implements EventHandler {

	@Autowired
	private PostgresEventStore eventStore;

	@Autowired
	private InternmentRepository internmentRepository;

	@Override
	public boolean isSubscribedTo(Event<?> event) {
		return event instanceof InternmentWasDeleted || event instanceof InternmentWasRegistered
				|| event instanceof InternmentWasUpdated;
	}

	@Override
	public void execute(Event<?> event) {
		Internment entity = eventStore.pull(Internment.class, event.getEntityId());

		com.xgh.model.query.internment.Internment internmentProjection = new com.xgh.model.query.internment.Internment(
				entity.getId().getValue(), entity.getBedId().getValue(), entity.getAnimalId().getValue(),
				entity.getBusyAt(), entity.getBusyUntil(), entity.isDeleted());

		internmentRepository.save(internmentProjection);
	}

}

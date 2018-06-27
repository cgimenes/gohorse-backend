package com.xgh.eventhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.event.Event;
import com.xgh.buildingblocks.event.EventHandler;
import com.xgh.model.command.owner.Owner;
import com.xgh.model.command.owner.events.OwnerWasDeleted;
import com.xgh.model.command.owner.events.OwnerWasRegistered;
import com.xgh.model.command.owner.events.OwnerWasUpdated;
import com.xgh.model.query.address.AddressProjector;
import com.xgh.model.query.owner.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OwnerProjector implements EventHandler {
	private final EventStore eventStore;
	private final OwnerRepository repository;
	private final AddressProjector addressProjector;

	@Autowired
	public OwnerProjector(EventStore eventStore, AddressProjector addressProjector, OwnerRepository repository) {
		this.eventStore = eventStore;
		this.addressProjector = addressProjector;
		this.repository = repository;
	}

	@Override
	public boolean isSubscribedTo(Event<?> event) {
		return event instanceof OwnerWasDeleted || event instanceof OwnerWasRegistered
				|| event instanceof OwnerWasUpdated;
	}

	@Override
	public void execute(Event<?> event) {
		Owner entity = eventStore.pull(Owner.class, event.getEntityId());

		com.xgh.model.query.address.Address addressProjection = addressProjector.execute(entity.getAddress());

		com.xgh.model.query.owner.Owner projection = new com.xgh.model.query.owner.Owner(entity.getId().getValue(),
				entity.getName().getValue(), entity.getDocument(), entity.getPhone().getValue(),
				entity.getBirthDate().getValue(), addressProjection, entity.isDeleted());

		repository.save(projection);
	}
}

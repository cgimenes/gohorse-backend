package com.xgh.eventhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.event.Event;
import com.xgh.buildingblocks.event.EventHandler;
import com.xgh.model.command.enumerators.Enumerator;
import com.xgh.model.command.enumerators.events.EnumeratorWasDeleted;
import com.xgh.model.command.enumerators.events.EnumeratorWasRegistered;
import com.xgh.model.command.enumerators.events.EnumeratorWasUpdated;
import com.xgh.model.query.enumerators.EnumeratorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnumeratorProjector implements EventHandler {
    private final EventStore eventStore;
    private final EnumeratorRepository repository;

    @Autowired
    public EnumeratorProjector(EventStore eventStore, EnumeratorRepository repository) {
        this.eventStore = eventStore;
        this.repository = repository;
    }

    @Override
    public boolean isSubscribedTo(Event<?> event) {
        return event instanceof EnumeratorWasDeleted
                || event instanceof EnumeratorWasRegistered
                || event instanceof EnumeratorWasUpdated;
    }

    @Override
    public void execute(Event<?> event) {
        Enumerator entity = eventStore.pull(Enumerator.class, event.getEntityId());

        com.xgh.model.query.enumerators.Enumerator projection = new com.xgh.model.query.enumerators.Enumerator(
                entity.getId().getValue(),
                entity.getKind().getValue(),
                entity.getName().getValue(),
                entity.isDeleted());

        repository.save(projection);
    }
}

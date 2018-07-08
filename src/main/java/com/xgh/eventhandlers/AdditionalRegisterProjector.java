package com.xgh.eventhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.event.Event;
import com.xgh.buildingblocks.event.EventHandler;
import com.xgh.model.command.additionalregister.AdditionalRegister;
import com.xgh.model.command.additionalregister.events.AdditionalRegisterWasDeleted;
import com.xgh.model.command.additionalregister.events.AdditionalRegisterWasRegistered;
import com.xgh.model.command.additionalregister.events.AdditionalRegisterWasUpdated;
import com.xgh.model.query.additionalregister.AdditionalRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdditionalRegisterProjector implements EventHandler {
    private final EventStore eventStore;
    private final AdditionalRegisterRepository repository;

    @Autowired
    public AdditionalRegisterProjector(EventStore eventStore, AdditionalRegisterRepository repository) {
        this.eventStore = eventStore;
        this.repository = repository;
    }

    @Override
    public boolean isSubscribedTo(Event<?> event) {
        return event instanceof AdditionalRegisterWasDeleted
                || event instanceof AdditionalRegisterWasRegistered
                || event instanceof AdditionalRegisterWasUpdated;
    }

    @Override
    public void execute(Event<?> event) {
        AdditionalRegister entity = eventStore.pull(AdditionalRegister.class, event.getEntityId());

        com.xgh.model.query.additionalregister.AdditionalRegister projection = new com.xgh.model.query.additionalregister.AdditionalRegister(
                entity.getId().getValue(),
                entity.getRegisterType().getValue(),
                entity.getValue().getValue(),
                entity.isDeleted());

        repository.save(projection);
    }
}

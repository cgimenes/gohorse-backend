package com.xgh.model.command.enumerators.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.enumerators.Enumerator;
import com.xgh.model.command.enumerators.commands.UpdateEnumerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnumeratorUpdate implements CommandHandler<UpdateEnumerator> {
    private final EventStore eventStore;

    @Autowired
    public EnumeratorUpdate(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public void execute(UpdateEnumerator command) {
        Enumerator enumerator = eventStore.pull(Enumerator.class, command.getId());
        enumerator.update(
                command.getGroup(),
                command.getName());
        eventStore.push(enumerator);
    }
}

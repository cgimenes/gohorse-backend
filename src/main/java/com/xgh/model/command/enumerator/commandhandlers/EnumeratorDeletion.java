package com.xgh.model.command.enumerator.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.enumerator.Enumerator;
import com.xgh.model.command.enumerator.commands.DeleteEnumerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnumeratorDeletion implements CommandHandler<DeleteEnumerator> {
    private final EventStore eventStore;

    @Autowired
    public EnumeratorDeletion(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public void execute(DeleteEnumerator command) {
        Enumerator enumerator = eventStore.pull(Enumerator.class, command.getId());
        enumerator.delete();
        eventStore.push(enumerator);
    }
}

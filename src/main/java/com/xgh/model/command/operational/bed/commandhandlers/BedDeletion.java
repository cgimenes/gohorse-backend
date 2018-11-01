package com.xgh.model.command.operational.bed.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.operational.bed.Bed;
import com.xgh.model.command.operational.bed.commands.DeleteBed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BedDeletion implements CommandHandler<DeleteBed> {
    private final EventStore eventStore;

    @Autowired
    protected BedDeletion(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public void execute(DeleteBed command) {
        Bed bed = eventStore.pull(Bed.class, command.getId());
        bed.delete();
        eventStore.push(bed);
    }
}

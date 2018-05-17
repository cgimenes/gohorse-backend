package com.xgh.model.command.bed.commandhandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.bed.Bed;
import com.xgh.model.command.bed.commands.DeleteBed;

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

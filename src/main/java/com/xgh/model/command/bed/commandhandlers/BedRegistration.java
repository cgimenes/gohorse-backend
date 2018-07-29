package com.xgh.model.command.bed.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.bed.Bed;
import com.xgh.model.command.bed.commands.RegisterBed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BedRegistration implements CommandHandler<RegisterBed> {
    private final EventStore eventStore;

    @Autowired
    public BedRegistration(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public void execute(RegisterBed command) {
        Bed bed = new Bed();
        bed.register(command.getId(), command.getCode());
        eventStore.push(bed);
    }
}

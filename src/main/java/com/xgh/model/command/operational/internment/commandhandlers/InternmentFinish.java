package com.xgh.model.command.operational.internment.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.operational.internment.Internment;
import com.xgh.model.command.operational.internment.commands.FinishInternment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InternmentFinish implements CommandHandler<FinishInternment> {
    private final EventStore repository;

    @Autowired
    public InternmentFinish(EventStore repository) {
        this.repository = repository;
    }

    @Override
    public void execute(FinishInternment command) {
    	Internment internment = repository.pull(Internment.class, command.getId());
    	internment.finish();
        repository.push(internment);
    }
}

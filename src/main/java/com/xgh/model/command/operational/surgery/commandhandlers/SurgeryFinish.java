package com.xgh.model.command.operational.surgery.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.operational.surgery.Surgery;
import com.xgh.model.command.operational.surgery.commands.FinishSurgery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SurgeryFinish implements CommandHandler<FinishSurgery> {
    private final EventStore repository;

    @Autowired
    public SurgeryFinish(EventStore repository) {
        this.repository = repository;
    }

    @Override
    public void execute(FinishSurgery command) {
    	Surgery surgery = repository.pull(Surgery.class, command.getId());
        surgery.finish();
        repository.push(surgery);
    }
}

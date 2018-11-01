package com.xgh.model.command.operational.surgery.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.operational.surgery.Surgery;
import com.xgh.model.command.operational.surgery.commands.CancelSurgery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SurgeryCancellation implements CommandHandler<CancelSurgery> {
    private final EventStore repository;

    @Autowired
    public SurgeryCancellation(EventStore repository) {
        this.repository = repository;
    }

    @Override
    public void execute(CancelSurgery command) {
    	Surgery surgery = repository.pull(Surgery.class, command.getId());
        surgery.cancel();
        repository.push(surgery);
    }
}

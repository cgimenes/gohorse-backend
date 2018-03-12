package com.xdg.xdg.laboratory;

import com.xdg.xdg.laboratory.commandhandlers.LaboratoryRegistration;
import com.xdg.xdg.laboratory.commands.RegisterLaboratory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LaboratoryCommandService {
    @Autowired
    private LaboratoryRepository repository;

    public LaboratoryId createLaboratory(RegisterLaboratory command) {
        LaboratoryRegistration handler = new LaboratoryRegistration(repository);
        handler.execute(command);
        return command.getId();
    }
}

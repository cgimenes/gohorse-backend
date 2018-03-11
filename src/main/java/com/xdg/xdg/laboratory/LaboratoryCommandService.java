package com.xdg.xdg.laboratory;

import com.xdg.xdg.laboratory.commandhandlers.LaboratoryRegistration;
import com.xdg.xdg.laboratory.commands.RegisterLaboratory;

public class LaboratoryCommandService {
    public LaboratoryId createLaboratory(RegisterLaboratory registerLaboratory) {
        LaboratoryRegistration handler = new LaboratoryRegistration();
        handler.execute(registerLaboratory);
        return registerLaboratory.getId();
    }
}

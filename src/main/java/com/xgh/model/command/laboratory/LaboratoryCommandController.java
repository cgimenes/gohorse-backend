package com.xgh.model.command.laboratory;

import com.xgh.infra.controller.CrudCommandController;
import com.xgh.model.command.laboratory.commands.DeleteLaboratory;
import com.xgh.model.command.laboratory.commands.RegisterLaboratory;
import com.xgh.model.command.laboratory.commands.UpdateLaboratory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/laboratories")
@RestController
public class LaboratoryCommandController extends CrudCommandController<RegisterLaboratory, UpdateLaboratory, DeleteLaboratory> {
    @Override
    protected String getBasePath() {
        return "/laboratories";
    }
}

package com.xgh.model.command.operational.internment;

import com.xgh.infra.controller.BasicCommandController;
import com.xgh.model.command.operational.internment.commands.DeleteInternment;
import com.xgh.model.command.operational.internment.commands.RegisterInternment;
import com.xgh.model.command.operational.internment.commands.UpdateInternment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internments")
public class InternmentCommandController
        extends BasicCommandController<RegisterInternment, UpdateInternment, DeleteInternment> {
    @Override
    protected String getBasePath() {
        return "/internments";
    }
}
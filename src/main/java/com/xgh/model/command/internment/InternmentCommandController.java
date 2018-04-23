package com.xgh.model.command.internment;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.xgh.infra.controller.BasicCommandController;
import com.xgh.model.command.internment.commands.DeleteInternment;
import com.xgh.model.command.internment.commands.RegisterInternment;
import com.xgh.model.command.internment.commands.UpdateInternment;

@RestController
@RequestMapping("/internments")
public class InternmentCommandController
        extends BasicCommandController<RegisterInternment, UpdateInternment, DeleteInternment> {
    @Override
    protected String getBasePath() {
        return "/internments";
    }
}
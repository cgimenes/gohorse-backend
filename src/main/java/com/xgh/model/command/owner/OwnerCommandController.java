package com.xgh.model.command.owner;

import com.xgh.infra.controller.BasicCommandController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xgh.model.command.owner.commands.DeleteOwner;
import com.xgh.model.command.owner.commands.RegisterOwner;
import com.xgh.model.command.owner.commands.UpdateOwner;

@RestController
@RequestMapping("/owners")
public class OwnerCommandController extends BasicCommandController<RegisterOwner, UpdateOwner, DeleteOwner> {
    @Override
    protected String getBasePath() {
        return "/owners";
    }
}

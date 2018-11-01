package com.xgh.model.command.operational.owner;

import com.xgh.infra.controller.BasicCommandController;
import com.xgh.model.command.operational.owner.commands.DeleteOwner;
import com.xgh.model.command.operational.owner.commands.RegisterOwner;
import com.xgh.model.command.operational.owner.commands.UpdateOwner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owners")
public class OwnerCommandController extends BasicCommandController<RegisterOwner, UpdateOwner, DeleteOwner> {
    @Override
    protected String getBasePath() {
        return "/owners";
    }
}

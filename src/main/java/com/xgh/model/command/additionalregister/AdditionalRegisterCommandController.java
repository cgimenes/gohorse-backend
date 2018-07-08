package com.xgh.model.command.additionalregister;

import com.xgh.infra.controller.BasicCommandController;
import com.xgh.model.command.additionalregister.commands.DeleteAdditionalRegister;
import com.xgh.model.command.additionalregister.commands.RegisterAdditionalRegister;
import com.xgh.model.command.additionalregister.commands.UpdateAdditionalRegister;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enums")
public class AdditionalRegisterCommandController extends BasicCommandController<RegisterAdditionalRegister, UpdateAdditionalRegister, DeleteAdditionalRegister> {
    @Override
    protected String getBasePath() {
        return "/enums";
    }
}
